package com.csg.warrior.service.impl;

import com.csg.warrior.KeyStringGenerator;
import com.csg.warrior.ServerConstants;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.KeyRequestService;
import com.csg.warrior.service.UserMobileKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KeyRequestServiceImpl implements KeyRequestService {
    @Autowired private UserDao userDao;
	@Autowired private UserMobileKeyService userMobileKeyService;
	
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
			System.out.println("WAR server keyrequest 1");
			MobileKey mobileKey = new MobileKey(KeyStringGenerator.generateKeyString());
			userMobileKeyService.save(param_user, mobileKey);
			return mobileKey.getKeyString();
			//gcm_sendWARKey(param_user, mobilekey);
		}
		else if (queriedUser.getDeviceID().equals(param_user.getDeviceID())) {
			System.out.println("WAR server keyrequest 2");
			MobileKey mobileKey = userMobileKeyService.getMobileKeyOfUser(queriedUser);
			System.out.println("This device already WAR registered." +
								"\nUsername: " + queriedUser.getUsername() +
								"\nWebsite: " + queriedUser.getWebsite() +
								"\nGCM Device ID: " + queriedUser.getDeviceID() +
								"\nWAR key: " + mobileKey.getKeyString());
			return mobileKey.getKeyString();
			//gcm_sendWARKey(param_user, mobileKey);
		}   
		else {
			System.out.println("WAR SERVER Queried user:" +
					"\nUsername: " + queriedUser.getUsername() +
					"\nWebsite: " + queriedUser.getWebsite() +
					"\nGCM Device ID: " + queriedUser.getDeviceID());
			
			System.out.println("WAR server keyrequest 3");
			return "You are an impostor.";			
			// TODO: action pag impostor (mismatch GCM device id)
		}
	}
}
