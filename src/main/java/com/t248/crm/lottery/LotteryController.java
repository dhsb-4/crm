package com.t248.crm.lottery;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/lottery")
public class LotteryController {

    @RequestMapping(value = "/enter")
    public String enter(){
        SecurityUtils.getSubject().logout();
        return "enter";
    }

}
