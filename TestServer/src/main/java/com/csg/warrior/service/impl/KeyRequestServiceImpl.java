package com.csg.warrior.service.impl;

import com.csg.warrior.KeyStringGenerator;
import com.csg.warrior.ServerConstants;
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
			return ServerConstants.messageDeviceAlreadyBLADERegistered;
		}
		else {
			System.out.println("BLADE Server: queried user:" +
					"\t\nUsername: " + queriedUser.getUsername() +
					"\t\nWebsite: " + queriedUser.getWebsite() +
					"\t\nBLADE UUID: " + queriedUser.getDeviceID());
			
			return ServerConstants.messageIncorrectDevice;		
		}
	}
}
