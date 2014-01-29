package com.csg.warrior.raydotcom.controller;

import com.csg.warrior.raydotcom.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import com.csg.warrior.raydotcom.WarriorConfig;
import com.csg.warrior.raydotcom.domain.User;
import com.csg.warrior.raydotcom.service.UserService;
import com.csg.warrior.raydotcom.service.WarriorService;

@Controller
@RequestMapping("/unlink-mobile")
public class UnlinkMobileController {
	WarriorConfig warriorConfig = new WarriorConfig();
	
	@Autowired private WarriorService warriorService;
	@Autowired private UserService userService;
    @Autowired private EmailSenderService emailSenderService;
	
	@RequestMapping(method= RequestMethod.GET)
	public String unlinkMobileHomepage(ModelMap model) {
		model.addAttribute("user", new User());
		return "unlinkmobile";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String unlinkMobile(User user) {
		if (userService.verifyUserPass(user)) {
            emailSenderService.sendMail("smitparaggua@yahoo.com", null, null);   // TODO CHANGE THIS LINE
			warriorService.forwardUnlinkRequestToWARServer(user.getUsername(), warriorConfig.getHostWebsite());
		}
		return "redirect:/login";
	}
}
