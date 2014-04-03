package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.core.BladeService;
import com.csg.warrior.raydotcom.WarriorConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    private WarriorConfig warriorConfig = new WarriorConfig();
    private BladeService bladeService = new BladeService(warriorConfig.getBLADEServerURL(), warriorConfig.getHostWebsite());

    @RequestMapping(value = "/login")
    public String getLoginPage() {
        return "login/login";
    }

    @RequestMapping("/login-success")
    public String successfulLogin() {
        return "redirect:/home";
    }

    @RequestMapping("/login-failed")
    public String loginFailed(ModelMap model) {
        model.addAttribute("error", "Invalid username or password");
        return "login/login";
    }

    @RequestMapping(value = "/blade/login", method= RequestMethod.POST)
    @ResponseBody
    public String acceptMobileKey(@RequestParam("username") String username,
    							  @RequestParam("bladeKey") String bladeKey,
                                  @RequestParam("bladeUUID") String bladeUUID) {
        return bladeService.sendMobileKey(username, bladeKey, bladeUUID);
    }
}
