package com.csg.warrior.controller;

import com.csg.warrior.service.QuadDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/quad-delete")
public class QuadDeleteController {
    @Autowired private QuadDeleteService quadDeleteService;

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public String processQuadDeleteRequest(@RequestParam("username") String username, 
    								@RequestParam("website") String website,
    								@RequestParam("bladeKey") String bladeKey,
    								@RequestParam("bladeUUID") String bladeUUID) {
    	return quadDeleteService.quadDelete(username, website, bladeUUID, bladeKey);
    }
    
    
}
