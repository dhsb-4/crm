package com.t248.crm.controller;

import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import com.t248.crm.service.user.RoleService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/list")
    public String findUsers(String usrName, Model model, @RequestParam(required = false, defaultValue = "0") Long roleId,
                            @RequestParam(required = false, defaultValue = "1") int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 5);
        Page<User> userPager = userService.findUsers(usrName, roleId, pageable);
        model.addAttribute("userPager", userPager);
        model.addAttribute("usrName", usrName);
        model.addAttribute("roleId", roleId);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        return "/user/list";
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        return "/user/add";
    }

    @RequestMapping(value = "/save")
    public String save(User user) {
        userService.addUser(user);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/edit")
    public String edit(Long usrId, Model model) {
        User user = userService.getUser(usrId);
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "user/edit";
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Map del(Long usrId) {
        userService.deleteUser(usrId);
        Map map = new HashMap();
        map.put("delResult", "true");
        return map;
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("-----没有权限-----");
        return "403";
    }

}
