package com.csg.warrior.service.impl;

import com.csg.warrior.KeyStringGenerator;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.KeyRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KeyRequestServiceImpl implements KeyRequestService {
    @Autowired private UserDao userDao;

	@Override
	public String checkWarriorRegistration(User param_user) {
		
    	/*
    	 * Possible situations:
    	 * 1.) First time cinlick yung "Request key" button --> no (username, website, device_id, warriorkey) yet
    	 * 2.) "This (username, website, device_id) already has a warriorkey"
    	 * 3.) "This (username, website) already exists but different device_id and/or warriorkey: you are an impostor"
    	 * 
    	 * Best order of checking: 
    	 * 2 --> regenerate key or ibigay na lang yung key?
    	 * 1 --> create quadruple
    	 * 3 --> LOLNOPE
    	 */
		
        User queriedUser = userDao.getUser(param_user.getUsername(), param_user.getWebsite());
        
		if(queriedUser == null) {
			System.out.println("BLADE Server: device not yet BLADE registered");
			MobileKey mobileKey = new MobileKey(KeyStringGenerator.generateKeyString());
            param_user.setMobileKey(mobileKey);
            userDao.save(param_user);
			return mobileKey.getKeyString();
		}
		else if (queriedUser.getDeviceID().equals(param_user.getDeviceID())) {
			System.out.println("BLADE Server: device already BLADE registered");
			return "From BLADE Server: device already BLADE registered";
		}
		else {
			System.out.println("BLADE Server: queried user:" +
					"\t\nUsername: " + queriedUser.getUsername() +
					"\t\nWebsite: " + queriedUser.getWebsite() +
					"\t\nBLADE UUID: " + queriedUser.getDeviceID());
			
			return "From BLADE Server: this is not the registered device";			
			// TODO: action pag impostor (mismatch GCM device id)
		}
	}
}
