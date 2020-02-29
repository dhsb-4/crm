package com.t248.crm.controller;

import com.t248.crm.entity.Chance;
import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import com.t248.crm.service.chance.ChanceService;
import com.t248.crm.service.dict.DictService;
import com.t248.crm.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/chance")
public class ChanceController {

    @Resource
    private ChanceService chanceService;

    @Resource
    private UserService userService;

    @Resource
    private DictService dictService;

    /**
     * 查询全部，分页
     *
     * @param chcCustName
     * @param chcTitle
     * @param model
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String chcCustName, String chcTitle, Model model,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 5);  //查询5条数据
        String chcStatus = "未分配";
        Page<Chance> pager = chanceService.finds(chcCustName, chcTitle, chcStatus, pageable);//返回查询条件

        model.addAttribute("pager", pager);
        model.addAttribute("chcCustName", chcCustName);
        model.addAttribute("chcTitle", chcTitle);
        return "chance/list";
    }

    /**
     * 跳转到新增
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        //查询总经理
        Role role = new Role();
        role.setRoleId(2L);
        //客户等级查询
        model.addAttribute("dictk", dictService.findByDictType("客户等级"));
        //地区查询
        model.addAttribute("dictd", dictService.findByDictType("地区"));
        model.addAttribute("users", userService.findAll(role));
        return "chance/add";
    }

    /**
     * 保存
     *
     * @param chance
     * @param session
     * @return
     */
    @RequestMapping(value = "/save")
    public String save(Chance chance, HttpSession session) {
        //找到登入的用户
        User user = (User) session.getAttribute("user");

        chance.setChcCreateId(user.getUsrId());
        chance.setChcCreateBy(user.getUsrName());
        chance.setChcCreateDate(new Date());

        chanceService.save(chance);
        return "redirect:/chance/list";
    }

    /**
     * 保存
     *
     * @param chance
     * @param session
     * @return
     */
    @RequestMapping(value = "/editsave")
    public String editsave(Chance chance, HttpSession session) {
        //找到登入的用户
        User user = (User) session.getAttribute("user");
        chance.setChcCreateId(user.getUsrId());
        chance.setChcCreateBy(user.getUsrName());
        //指派角色
        if (chance.getUser() != null) {
            chance.setChcDueTo(chance.getUser().getUsrName());
        }

        chanceService.save(chance);
        return "redirect:/chance/list";
    }

    /**
     * 跳转到指派页面
     *
     * @param model
     * @param chcId
     * @return
     */
    @RequestMapping(value = "/assign")
    public String assign(Model model, Long chcId) {
        //查询所指派的用户
        Chance chance = chanceService.eait(chcId);

        Role role = new Role();
        role.setRoleId(2L);
        model.addAttribute("chance", chance);
        model.addAttribute("users", userService.findAll(role));//查询总经理
        return "chance/assign";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long chcId, Model model) {
        Chance chance = chanceService.eait(chcId);
        Role role = new Role();
        role.setRoleId(2L);

        model.addAttribute("chance", chance);
        model.addAttribute("dictk", dictService.findByDictType("客户等级"));
        model.addAttribute("dictd", dictService.findByDictType("地区"));
        model.addAttribute("users", userService.findAll(role));
        return "chance/edit";
    }

    /**
     * 删除
     *
     * @param chcId
     * @return
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Map del(Long chcId) {

        chanceService.del(chcId);
        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }
}
