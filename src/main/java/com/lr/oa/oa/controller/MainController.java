package com.lr.oa.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class MainController {

    @RequestMapping("main")
    public String main(){

        return "main";

    }
}
