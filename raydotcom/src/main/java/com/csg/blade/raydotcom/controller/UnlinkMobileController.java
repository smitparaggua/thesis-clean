package com.csg.blade.raydotcom.controller;

import com.csg.blade.raydotcom.domain.User;
import com.csg.blade.raydotcom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/unlink-mobile")
public class UnlinkMobileController {
	@Autowired private UserService userService;

	@RequestMapping(method= RequestMethod.GET)
	public String unlinkMobileHomepage(ModelMap model) {
		model.addAttribute("user", new User());
		return "unlink-mobile";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String unlinkMobile(User user, ModelMap model) {
        try {
            userService.unlinkMobileOf(user);
        } catch(IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "unlink-mobile";
        }
		return "redirect:/login";
	}
}
