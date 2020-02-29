package com.t248.crm.controller;

import com.t248.crm.entity.Chance;
import com.t248.crm.entity.Lost;
import com.t248.crm.service.lost.LostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/lost")
public class LostController {
    @Resource
    private LostService lostService;

    /**
     * 分页查询
     *
     * @param pageIndex
     * @param lstCustName
     * @param lstCustManagerName
     * @param lstStatus
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String lsit(@RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String lstCustName
            , @RequestParam(required = false) String lstCustManagerName
            , @RequestParam(required = false) String lstStatus
            , Model model) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 2);  //查询5条数据
        Page<Lost> pager = lostService.finds(lstCustName, lstCustManagerName, lstStatus, pageable);

        model.addAttribute("lstCustName", lstCustName);
        model.addAttribute("lstCustManagerName", lstCustManagerName);
        model.addAttribute("lstStatus", lstStatus);
        model.addAttribute("pager", pager);
        return "lost/list";
    }

    /**
     * 跳转到暂缓页面
     *
     * @param lstId
     * @param model
     * @return
     */
    @RequestMapping(value = "/postpone")
    public String postpone(Long lstId, Model model) {
        model.addAttribute("lost", lostService.findByLstId(lstId));
        return "lost/postpone";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save")
    public String save(Lost lost) {
        lostService.save(lost);
        return "redirect:/lost/list";
    }

    /**
     * 跳转到暂缓页面
     *
     * @param lstId
     * @param model
     * @return
     */
    @RequestMapping(value = "/confirm")
    public String confirm(Long lstId, Model model) {
        model.addAttribute("lost", lostService.findByLstId(lstId));
        return "lost/confirm";
    }


}
