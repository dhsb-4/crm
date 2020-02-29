package com.t248.crm.controller;

import com.t248.crm.entity.Dict;
import com.t248.crm.entity.Product;
import com.t248.crm.entity.Storage;
import com.t248.crm.service.dict.DictService;
import com.t248.crm.service.product.ProductService;
import com.t248.crm.service.storage.StorageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/dict")
public class DictController {

    @Resource
    public DictService dictService;


    @RequestMapping(value = "/gradeList")
    public String gradeList(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String dictItem, @RequestParam(required = false) String dictValue) {
        String name = "gradeList";

        Pageable pageable = PageRequest.of(pageIndex - 1, 5);  //查询5条数据
        Page<Dict> pager = dictService.findl(dictValue, dictItem, pageable, name);
        model.addAttribute("dictItem", dictItem);
        model.addAttribute("dictValue", dictValue);
        model.addAttribute("pager", pager);
        return "dict/gradeList";
    }

    @RequestMapping(value = "/serveList")
    public String serveList(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String dictItem, @RequestParam(required = false) String dictValue) {
        String name = "serveList";

        Pageable pageable = PageRequest.of(pageIndex - 1, 5);  //查询5条数据
        Page<Dict> pager = dictService.findl(dictValue, dictItem, pageable, name);
        model.addAttribute("dictItem", dictItem);
        model.addAttribute("dictValue", dictValue);
        model.addAttribute("pager", pager);
        return "dict/serveList";
    }

    @RequestMapping(value = "/dealList")
    public String dealList(Model model, @RequestParam(required = false, defaultValue = "1") int pageIndex
            , @RequestParam(required = false) String dictItem, @RequestParam(required = false) String dictValue) {
        String name = "dealList";

        Pageable pageable = PageRequest.of(pageIndex - 1, 5);  //查询5条数据
        Page<Dict> pager = dictService.findl(dictValue, dictItem, pageable, name);
        model.addAttribute("dictItem", dictItem);
        model.addAttribute("dictValue", dictValue);
        model.addAttribute("pager", pager);
        return "dict/dealList";
    }


    @RequestMapping(value = "gadd")
    public String gadd(Model model) {
        model.addAttribute("add", "gadd");
        return "dict/add";
    }

    @RequestMapping(value = "sadd")
    public String sadd(Model model) {
        model.addAttribute("add", "sadd");
        return "dict/add";
    }

    @RequestMapping(value = "dadd")
    public String dadd(Model model) {
        model.addAttribute("add", "dadd");
        return "dict/add";
    }

    @RequestMapping(value = "/gedit")
    public String gedit(Model model, Long dictId) {
        model.addAttribute("add", "gadd");
        model.addAttribute("dict", dictService.findByDictId(dictId));
        return "dict/edit";
    }

    @RequestMapping(value = "/sedit")
    public String sedit(Model model, Long dictId) {
        model.addAttribute("add", "sadd");
        model.addAttribute("dict", dictService.findByDictId(dictId));
        return "dict/edit";
    }

    @RequestMapping(value = "/dedit")
    public String dedit(Model model, Long dictId) {
        model.addAttribute("add", "dadd");
        model.addAttribute("dict", dictService.findByDictId(dictId));
        return "dict/edit";
    }

    @RequestMapping(value = "/save")
    public String save(String add, String dictItem) {
        if (add.equals("gadd")) {
            int n = dictService.getMax() + 1;
            Dict dict = new Dict();
            dict.setDictType("客户等级");
            dict.setDictIsEditable(0L);
            dict.setDictItem(dictItem);
            dict.setDictValue(n + "");
            dictService.save(dict);
            return "redirect:/dict/gradeList";
        }

        if (add.equals("sadd")) {
            Dict dict = new Dict();
            dict.setDictType("服务类型");
            dict.setDictIsEditable(1L);
            dict.setDictItem(dictItem);
            dict.setDictValue(dictItem);
            dictService.save(dict);
            return "redirect:/dict/serveList";
        }

        if (add.equals("dadd")) {
            Dict dict = new Dict();
            dict.setDictType("地区");
            dict.setDictIsEditable(1L);
            dict.setDictItem(dictItem);
            dict.setDictValue(dictItem);
            dictService.save(dict);
            return "redirect:/dict/dealList";
        }

        return "";
    }

    @RequestMapping(value = "/updsave")
    public String updsave(String add, String dictItem, Long dictId) {
        if (add.equals("gadd")) {
            Dict dict = dictService.findByDictId(dictId);
            dict.setDictItem(dictItem);
            dictService.save(dict);
            return "redirect:/dict/gradeList";
        }
        if (add.equals("sadd")) {
            Dict dict = dictService.findByDictId(dictId);
            dict.setDictItem(dictItem);
            dict.setDictValue(dictItem);
            dictService.save(dict);
            return "redirect:/dict/serveList";
        }
        if (add.equals("dadd")) {
            Dict dict = dictService.findByDictId(dictId);
            dict.setDictItem(dictItem);
            dict.setDictValue(dictItem);
            dictService.save(dict);
            return "redirect:/dict/dealList";
        }

        return "";
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Map del(Long dictId) {

        dictService.Del(dictId);
        Map map = new HashMap();
        //返回true
        map.put("delResult", "true");
        return map;
    }


}
