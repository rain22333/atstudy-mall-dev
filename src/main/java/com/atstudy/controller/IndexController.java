package com.atstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","/index"})
public class IndexController {

    @RequestMapping({"/","/index"})
    public String index(){
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

}
