package com.atstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "index/error";
    }

}
