package com.t248.crm.controller;

import com.t248.crm.entity.*;
import com.t248.crm.service.activity.ActivityService;
import com.t248.crm.service.customer.CustomerService;
import com.t248.crm.service.dict.DictService;
import com.t248.crm.service.linkman.LinkmanService;
import com.t248.crm.service.orders.OrdersService;
import com.t248.crm.service.ordersline.OrdersLineService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @Resource
    private DictService dictService;

    @Resource
    private LinkmanService linkmanService;

    @Resource
    private ActivityService activityService;

    @Resource
    private OrdersService ordersService;

    @Resource
    private OrdersLineService ordersLineService;

    /**
     * 分页加跳转
     *
     * @param pageIndex
     * @param custName
     * @param custNo
     * @param custRegion
     * @param custManagerName
     * @param custLevelLabel
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(@RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String custName         //客户名称
            , @RequestParam(required = false) String custNo           //客户编号
            , @RequestParam(required = false) String custRegion       //地区
            , @RequestParam(required = false) String custManagerName  //客户经理
            , @RequestParam(required = false) String custLevelLabel   //客户等级
            , Model model) {

        Pageable pageable = PageRequest.of(pageIndex - 1, 5);  //查询5条数据
        Page<Customer> pager = customerService.finds(custName, custNo, custRegion, custManagerName, custLevelLabel, pageable);

        model.addAttribute("pager", pager);
        model.addAttribute("custName", custName);
        model.addAttribute("custNo", custNo);
        model.addAttribute("custRegion", custRegion);
        model.addAttribute("custManagerName", custManagerName);
        model.addAttribute("custLevelLabel", custLevelLabel);

        //客户等级查询
        model.addAttribute("dictk", dictService.findByDictType("客户等级"));
        //地区查询
        model.addAttribute("dictd", dictService.findByDictType("地区"));

        return "customer/list";
    }

    /**
     * 跳转修改页面
     */
    @RequestMapping(value = "/edit")
    public String edit(String custNo, Model model) {
        //客户等级查询
        model.addAttribute("dictk", dictService.findByDictType("客户等级"));
        //地区查询
        model.addAttribute("dictd", dictService.findByDictType("地区"));
        //根据id查询
        model.addAttribute("customer", customerService.findByCustNo(custNo));

        return "/customer/edit";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/save")
    public String save(Customer customer, HttpServletResponse response) {
        customerService.save(customer);
        return "redirect:/customer/list";
    }

    /**
     * 跳转到联系人
     */
    @RequestMapping(value = "/contact")
    public String contact(String custNo, Model model) {
        Customer customer = customerService.findByCustNo(custNo);
        //根据id查询
        model.addAttribute("customer", customer);
        //根据客户编号查询
        model.addAttribute("linkman", linkmanService.findLinkmanList(custNo));
        return "customer/contact";
    }

    /**
     * 联系人修改
     */
    @RequestMapping(value = "/ledit")
    public String linmkanedit(Long lkmId, Model model) {
        model.addAttribute("linmkan", linkmanService.findByLkmId(lkmId));
        return "customer/linmkan/edit";
    }

    /**
     * 修改保存
     */
    @RequestMapping(value = "/lsave")
    public String linmkansave(Linkman linkman) {
        linkmanService.findByQuery(linkman);
        return "redirect:/customer/contact?custNo=" + linkman.getLkmCustNo();
    }

    /**
     * 跳转新建联系人
     */
    @RequestMapping(value = "/ladd")
    public String linmkanadd(Model model, String custName, String custNo) {
        model.addAttribute("custName", custName);
        model.addAttribute("custNo", custNo);
        return "customer/linmkan/add";
    }

    /**
     * 联系人删除
     */
    @RequestMapping(value = "/ldel")
    @ResponseBody
    public Map del(Long lkmId) {
        linkmanService.findDel(lkmId);
        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }

    /**
     * 交往记录列表
     */
    @RequestMapping(value = "/activity")
    public String activity(String custNo, Model model) {
        Customer customer = customerService.findByCustNo(custNo);
        model.addAttribute("customer", customer);
        model.addAttribute("activity", activityService.findActivityList(custNo));
        return "customer/activity/list";
    }

    /**
     * 编辑交往记录
     */
    @RequestMapping(value = "aedit")
    public String aedit(Long atvId, Model model) {
        model.addAttribute("activity", activityService.findByAtvId(atvId));
        return "customer/activity/edit";
    }

    /**
     * 编辑保存
     */
    @RequestMapping(value = "asave")
    public String aedit(Activity activity) {
        activityService.findSave(activity);
        return "redirect:/customer/activity?custNo=" + activity.getAtvCustNo();
    }

    /**
     * 交往记录新建
     */
    @RequestMapping(value = "aadd")
    public String aadd(Model model, String custName, String custNo) {
        model.addAttribute("custName", custName);
        model.addAttribute("custNo", custNo);
        return "customer/activity/add";
    }

    /**
     * 交往记录删除
     */
    @RequestMapping(value = "/adel")
    @ResponseBody
    public Map adel(Long atvId) {
        activityService.findDel(atvId);
        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }

    /**
     * 订单分页查询
     */
    @RequestMapping(value = "/olist")
    public String olist(@RequestParam(required = false, defaultValue = "1") int pageIndex, Model model, String custNo
            , String custName) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 2);
        Page<Orders> page = ordersService.finds(pageable, custNo);
        model.addAttribute("pager", page);
        model.addAttribute("customer", customerService.findByCustNo(custNo));
        model.addAttribute("custNo", custNo);
        model.addAttribute("custName", custName);
        return "customer/orders/list";
    }

    /**
     * 订单明细页面
     */
    @RequestMapping(value = "/detail")
    public String detail(Long odrId, Model model) {

        double conut = 0;

        Orders orders = new Orders();
        orders.setOdrId(odrId);
        List<OrdersLine> ordersLines = ordersLineService.findByOrders(orders);
        for (OrdersLine o : ordersLines) {
            conut += o.getOddPrice();
        }
        model.addAttribute("conut", conut);
        model.addAttribute("ordersline", ordersLines);
        model.addAttribute("orders", ordersService.findByOdrId(odrId));


        return "customer/orders/detail";

    }

    @RequestMapping(value = "cdel")
    @ResponseBody
    public Map odel(Long custId) {

        Customer customer = customerService.findByCustId(custId);

        activityService.aDel(customer.getCustNo());
        linkmanService.lDel(customer.getCustNo());
        customerService.cDel(customer.getCustNo());
        ordersService.oDel(customer.getCustNo());

        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }

}
