package com.t248.crm.controller;

import com.t248.crm.entity.Right;
import com.t248.crm.entity.Role;
import com.t248.crm.entity.SysRoleRight;
import com.t248.crm.service.user.RightService;
import com.t248.crm.service.user.RoleService;
import com.t248.crm.service.user.SysRoleRightService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private RightService rightService;

    @Resource
    private SysRoleRightService sysRoleRightService;

    @RequestMapping(value = "/json")
    @ResponseBody
    public List<Role> findAllRoles() {
        List<Role> list = roleService.findAllRoles();
        return list;
    }

    @RequestMapping(value = "/list")
    public String findUsers(String roleName, Model model,
                            @RequestParam(required = false, defaultValue = "1") int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, 2);
        Page<Role> userPager = roleService.findRoles(roleName, pageable);
        model.addAttribute("userPager", userPager);
        model.addAttribute("roleName", roleName);
        return "role/list";
    }

    @RequestMapping(value = "/save")
    public String save(Role role, String[] rightText) {

        if (role.getRoleId() != null) {
            sysRoleRightService.sDel(role.getRoleId());
            System.out.println(111);
        }


        List<SysRoleRight> roleRightList = new ArrayList<>();
        for (int i = 0; i < rightText.length; i++) {
            SysRoleRight sysRoleRight = new SysRoleRight();
            sysRoleRight.setRole(role);
            sysRoleRight.setRight(new Right(rightText[i]));
            roleRightList.add(sysRoleRight);
        }
        role.getSysRoleRights().addAll(roleRightList);
        roleService.save(role);
        return "redirect:/role/list";
    }

    @RequestMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("right", rightService.findAllRights());
        model.addAttribute("right1", rightService.findAllRights());
        return "role/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(Model model, Long roleId) {
        Role role = roleService.finadAll(roleId);
        model.addAttribute("role", role);
        model.addAttribute("right", rightService.findAllRights());
        model.addAttribute("right1", rightService.findAllRights());

        Map<String, String> map = new HashMap<>();
        for (SysRoleRight r : role.getSysRoleRights()) {
            map.put(r.getRight().getRightCode(), r.getRight().getRightCode());
        }
        model.addAttribute("map", map);
        return "role/edit";
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public Map del(Long roleId) {
        roleService.deleteRole(roleId);
        Map map = new HashMap();
        map.put("delResult", "true");
        return map;
    }
}
