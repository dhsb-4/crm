package com.t248.crm.controller;

import com.t248.crm.entity.Customer;
import com.t248.crm.entity.CstService;
import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import com.t248.crm.service.customer.CustomerService;
import com.t248.crm.service.dict.DictService;
import com.t248.crm.service.service.CstServiceService;
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
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/service")
public class CstServiceController {
    @Resource
    private CustomerService customerService;

    @Resource
    private DictService dictService;

    @Resource
    private CstServiceService cstServiceService;

    @Resource
    private UserService userService;

    /**
     * 跳转到服务创建页面
     *
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        model.addAttribute("usrId", user.getUsrId());
        model.addAttribute("usrName", user.getUsrName());
        model.addAttribute("customer", customerService.findList());
        model.addAttribute("dict", dictService.findByDictType("服务类型"));
        return "service/add";
    }

    /**
     * 新创建
     *
     * @param service
     * @param custNo
     * @return
     */
    @RequestMapping(value = "/save")
    public String save(CstService service, String custNo) {
        Customer customer = customerService.findByCustNo(custNo);
        service.setCustomer(customer);
        service.setSvrCustName(customer.getCustName());
        cstServiceService.save(service);
        return "redirect:/service/add";
    }

    /**
     * 跳转到服务分配页面
     */
    @RequestMapping(value = "/dispatch")
    public String dispatch(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String svrCustName
            , @RequestParam(required = false) String svrTitle
            , @RequestParam(required = false) String svrType) {
        //查询每页3条数据
        Pageable pageable = PageRequest.of(pageIndex - 1, 3);
        String condition = "dispatch";
        Page<CstService> pager = cstServiceService.finds(svrCustName, svrTitle, svrType, pageable, condition);

        model.addAttribute("svrCustName", svrCustName);
        model.addAttribute("svrTitle", svrTitle);
        model.addAttribute("svrType", svrType);
        model.addAttribute("dict", dictService.findByDictType("服务类型"));
        model.addAttribute("pager", pager);

        Role role = new Role();
        role.setRoleId(2L);
        model.addAttribute("user", userService.findAll(role));

        return "service/dispatch";
    }


    @RequestMapping(value = "/upd")
    @ResponseBody
    public Map upd(Long svrId, Long usrId) {
        CstService cstService = cstServiceService.findBySvrId(svrId);
        User user = userService.getUser(usrId);
        cstService.setSvrDueId(user.getUsrId());
        cstService.setSvrDueTo(user.getUsrName());
        cstService.setSvrDueDate(new Date());
        cstService.setSvrStatus("已分配");

        cstServiceService.save(cstService);

        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");

        return map;
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Map del(Long svrId) {
        cstServiceService.del(svrId);

        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");

        return map;
    }

    @RequestMapping(value = "/dealList")
    public String dealList(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String svrCustName
            , @RequestParam(required = false) String svrTitle
            , @RequestParam(required = false) String svrType) {
        //查询每页3条数据
        Pageable pageable = PageRequest.of(pageIndex - 1, 3);
        String condition = "dealList";
        Page<CstService> pager = cstServiceService.finds(svrCustName, svrTitle, svrType, pageable, condition);

        model.addAttribute("svrCustName", svrCustName);
        model.addAttribute("svrTitle", svrTitle);
        model.addAttribute("svrType", svrType);
        model.addAttribute("dict", dictService.findByDictType("服务类型"));
        model.addAttribute("pager", pager);

        return "service/dealList";
    }

    @RequestMapping(value = "/dispose")
    public String dispose(Model model, HttpSession session, Long svrId) {
        User user = (User) session.getAttribute("user");
        CstService cstService = cstServiceService.findBySvrId(svrId);
        model.addAttribute("user", user);
        model.addAttribute("service", cstService);
        return "service/dispose";
    }

    @RequestMapping(value = "/dsave")
    public String dsave(Long svrId, String svrDeal, HttpSession session) {
        CstService cstService = cstServiceService.findBySvrId(svrId);
        User user = (User) session.getAttribute("user");

        cstService.setSvrDeal(svrDeal);
        cstService.setSvrDealId(user.getUsrId());
        cstService.setSvrDealBy(user.getUsrName());
        cstService.setSvrDealDate(new Date());
        cstService.setSvrStatus("已处理");

        cstServiceService.save(cstService);

        return "redirect:/service/dealList";
    }

    @RequestMapping(value = "/feedbackList")
    public String feedbackList(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String svrCustName
            , @RequestParam(required = false) String svrTitle
            , @RequestParam(required = false) String svrType) {
        //查询每页3条数据
        Pageable pageable = PageRequest.of(pageIndex - 1, 3);
        String condition = "feedbackList";
        Page<CstService> pager = cstServiceService.finds(svrCustName, svrTitle, svrType, pageable, condition);

        model.addAttribute("svrCustName", svrCustName);
        model.addAttribute("svrTitle", svrTitle);
        model.addAttribute("svrType", svrType);
        model.addAttribute("dict", dictService.findByDictType("服务类型"));
        model.addAttribute("pager", pager);

        return "service/feedbackList";
    }

    @RequestMapping(value = "/feedbackdispose")
    public String feedbackdispose(Model model, Long svrId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        CstService cstService = cstServiceService.findBySvrId(svrId);
        model.addAttribute("user", user);
        model.addAttribute("service", cstService);
        return "service/feedbackdispose";
    }

    @RequestMapping(value = "/fsave")
    public String fsave(Long svrId, HttpSession session, String usrName, Date svrDealDate, String svrResult, Long svrSatisfy) {
        CstService cstService = cstServiceService.findBySvrId(svrId);
        User user = (User) session.getAttribute("user");

        cstService.setSvrDealId(user.getUsrId());
        cstService.setSvrDealBy(usrName);
        cstService.setSvrDealDate(svrDealDate);
        if (svrSatisfy >= 3L) {
            cstService.setSvrStatus("已归档");
        } else {
            cstService.setSvrStatus("已分配");
        }

        cstService.setSvrSatisfy(svrSatisfy);

        cstService.setSvrResult(svrResult);

        cstServiceService.save(cstService);

        return "redirect:/service/feedbackList";
    }


    @RequestMapping(value = "/archList")
    public String archList(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String svrCustName
            , @RequestParam(required = false) String svrTitle
            , @RequestParam(required = false) String svrType) {
        //查询每页3条数据
        Pageable pageable = PageRequest.of(pageIndex - 1, 3);
        String condition = "archList";
        Page<CstService> pager = cstServiceService.finds(svrCustName, svrTitle, svrType, pageable, condition);

        model.addAttribute("svrCustName", svrCustName);
        model.addAttribute("svrTitle", svrTitle);
        model.addAttribute("svrType", svrType);
        model.addAttribute("dict", dictService.findByDictType("服务类型"));
        model.addAttribute("pager", pager);

        return "service/archList";
    }

    @RequestMapping(value = "/query")
    public String query(Model model, Long svrId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        CstService cstService = cstServiceService.findBySvrId(svrId);

        String svrSatisfy = "";
        if (cstService.getSvrSatisfy() == 1L) {
            svrSatisfy = "❤";
        } else if (cstService.getSvrSatisfy() == 2L) {
            svrSatisfy = "❤❤";
        } else if (cstService.getSvrSatisfy() == 3L) {
            svrSatisfy = "❤❤❤";
        } else if (cstService.getSvrSatisfy() == 4L) {
            svrSatisfy = "❤❤❤❤";
        } else if (cstService.getSvrSatisfy() == 5L) {
            svrSatisfy = "❤❤❤❤❤";
        }

        model.addAttribute("svrSatisfy", svrSatisfy);
        model.addAttribute("user", user);
        model.addAttribute("service", cstService);
        return "service/query";
    }

}
