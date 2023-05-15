package com.atstudy.controller;

import com.atstudy.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/","/index"})
@Slf4j
public class IndexController {



    @RequestMapping("/success")
    public String success(){

        return "index/success";
    }

    @RequestMapping({"/","/index"})
    public String index(Authentication auth, Model model){
        // 获取到用户对象
        Admin admin = (Admin) auth.getPrincipal();
        log.info(admin.toString());
        // 将用户存储到请求域中
        model.addAttribute("admin",admin);
        return "index/index";
    }



    @RequestMapping("/login")
    public String login(){
        return "index/login";
    }

    @RequestMapping("/home")
    public String home(){
        return "index/home";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "index/error";
    }

}
