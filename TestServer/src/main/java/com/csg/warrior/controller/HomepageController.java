package com.csg.warrior.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
    @RequestMapping("/home")
    public String showHomePage() {
        return "homepage";
    }
}
