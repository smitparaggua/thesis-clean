package com.csg.warrior.raydotcom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;import java.lang.String;

@Controller
public class HomepageController {
    @RequestMapping("/home")
    public String showHomePage() {
        return "homepage";
    }
}
