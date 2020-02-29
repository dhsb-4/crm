package com.t248.crm.controller;

import com.t248.crm.entity.User;
import com.t248.crm.service.user.RightService;
import com.t248.crm.service.user.RoleService;
import com.t248.crm.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {
    @Resource
    private RightService rightService;

    @RequestMapping(value = "/main")
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/login")
    public String login(HttpSession session) {
        //删除session
        session.removeAttribute("user");
        SecurityUtils.getSubject().logout();
        return "login";
    }

    @RequestMapping(value = "/403")
    public String s(HttpSession session) {
        return "403";
    }

    @RequestMapping(value = "/dologin")
    public String dologin(String usrName, String usrPassword, HttpServletResponse response, Map<String, Object> map, HttpSession session) {
/*        User user = userService.login(usrName,usrPassword);
        if (user != null){
            request.getSession().setAttribute("user",user);
            return "main";
        }else{
            request.setAttribute("message","登入失败，用户或密码错误");
            return "login";
        }*/
        User user = null;
        try {
            //此处不再处理登录，由shiro进行处理
            AuthenticationToken token = new UsernamePasswordToken(usrName, usrPassword);
            SecurityUtils.getSubject().login(token);//调用Shiro认证
            user = (User) SecurityUtils.getSubject().getPrincipal();
            //注意此处session.setAttribute中的key值
            //需要和AuthorizationInterceptor拦截器sessino的key值
            session.setAttribute("user", user);
            session.setAttribute("r1", rightService.findAllRights());
            session.setAttribute("r2", rightService.findAllRights());
        } catch (IncorrectCredentialsException i) {
            i.printStackTrace();
            map.put("msg", "密码错误" + i.getMessage());
            SecurityUtils.getSubject().logout();
            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
            SecurityUtils.getSubject().logout();
            return "login";
        }
        return "main";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }


}
