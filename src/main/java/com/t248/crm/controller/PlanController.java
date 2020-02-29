package com.t248.crm.controller;

import com.t248.crm.entity.Chance;
import com.t248.crm.entity.Customer;
import com.t248.crm.entity.Dict;
import com.t248.crm.entity.Plan;
import com.t248.crm.service.chance.ChanceService;
import com.t248.crm.service.customer.CustomerService;
import com.t248.crm.service.dict.DictService;
import com.t248.crm.service.plan.PlanService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/plan")
public class PlanController {

    @Resource
    private ChanceService chanceService;

    @Resource
    private PlanService planService;

    @Resource
    private CustomerService customerService;

    @Resource
    private DictService dictService;

    /**
     * 分页查询
     *
     * @param chcCustName
     * @param chcTitle
     * @param chcLinkman
     * @param model
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(String chcCustName, String chcTitle, String chcLinkman, Model model,
                       @RequestParam(required = false, defaultValue = "1") int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 5);  //查询5条数据
        Page<Chance> pager = chanceService.planfind(chcCustName, chcTitle, chcLinkman, pageable);//返回查询条件

        model.addAttribute("pager", pager);
        model.addAttribute("chcCustName", chcCustName);
        model.addAttribute("chcTitle", chcTitle);
        model.addAttribute("chcLinkman", chcLinkman);
        return "plan/list";

    }

    /**
     * 跳转到执行计划页面
     *
     * @param chcId
     * @param model
     * @return
     */
    @RequestMapping(value = "/formulate")
    public String formulate(Long chcId, Model model) {
        model.addAttribute("chance", chanceService.eait(chcId));         //查询客户信息
        model.addAttribute("plans", planService.planChcIdAll(chcId));    //查询该客户的计划项
        return "plan/formulate";
    }

    /**
     * 删除计划项
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public Map del(Long plaId) {

        planService.planDel(plaId);

        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }

    /**
     * 修改计划项
     */
    @RequestMapping(value = "/upd")
    @ResponseBody
    public void upd(Long plaId, String plaTodo) {
        planService.planUpd(plaId, plaTodo);
    }

    /**
     * 添加计划项
     */
    @RequestMapping(value = "save")
    public String save(Plan plan, Long chcId, Model model) {
        plan.setPlaChcId(chcId);
        planService.planSave(plan);
        model.addAttribute("chance", chanceService.eait(chcId));         //查询客户信息
        model.addAttribute("plans", planService.planChcIdAll(chcId));    //查询该客户的计划项
        return "plan/formulate";
    }

    /**
     * 跳转到执行计划
     */
    @RequestMapping(value = "carry")
    public String carry(Long chcId, Model model) {
        model.addAttribute("chance", chanceService.eait(chcId));         //查询客户信息
        model.addAttribute("plans", planService.planChcIdAll(chcId));    //查询该客户的计划项
        return "plan/carry";
    }

    /**
     * 保存执行计划
     */
    @RequestMapping(value = "out")
    @ResponseBody
    public Map out(Long plaId, String plaResult) {
        planService.findOut(plaResult, plaId);

        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }

    /**
     * 查询
     *
     * @param chcId
     * @return
     */
    @RequestMapping(value = "inquire")
    public String inquire(Long chcId, Model model) {
        model.addAttribute("chance", chanceService.eait(chcId));         //查询客户信息
        model.addAttribute("plans", planService.planChcIdAll(chcId));    //查询该客户的计划项
        return "plan/inquire";
    }

    /**
     * 开发成功
     *
     * @param chcId
     * @return
     */
    @RequestMapping(value = "succeed")
    @ResponseBody
    public Map succeed(Long chcId) {
        Chance chance = chanceService.eait(chcId);
        Customer customer = new Customer();
        customer.setCustNo("KH0712070322" + (int) ((Math.random() * 9 + 1) * 10000));
        customer.setCustName(chance.getChcCustName());
        customer.setCustRegion(chance.getCustRegion());
        customer.setCustManagerId(chance.getChcId());
        customer.setCustManagerName(chance.getChcLinkman());
        customer.setCustLevelLabel(chance.getCustLevelLabel());
        Dict dict = dictService.findByDictItem(chance.getCustLevelLabel());
        customer.setCustLevel(dict.getDictValue());
        customer.setCustSatisfy(1L);
        customerService.finQuery(customer);

        chanceService.finUpd(chcId, "已归档");

        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }

    /**
     * 开发失败
     */
    @RequestMapping(value = "come")
    @ResponseBody
    public Map come(Long chcId) {
        chanceService.finUpd(chcId, "已归档");

        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }
}
