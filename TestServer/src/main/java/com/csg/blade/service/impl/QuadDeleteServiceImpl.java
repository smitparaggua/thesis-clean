package com.csg.blade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.blade.ServerConstants;
import com.csg.blade.dao.UserDao;
import com.csg.blade.domain.User;
import com.csg.blade.service.QuadDeleteService;

@Service
@Transactional
public class QuadDeleteServiceImpl implements QuadDeleteService {
	@Autowired private UserDao userDao;

	@Override
	public String quadDelete(String param_user_username, 
							String param_user_website,
							String param_user_bladeUUID,
							String param_user_bladeKey) {
		
        User queriedUser = userDao.getUser(param_user_username, param_user_website);
        
        System.out.println("Deleting quad:");
        System.out.println("USer: " + queriedUser.getUsername());
        System.out.println("Website: " + queriedUser.getWebsite());
        System.out.println("Blade UUID: " + queriedUser.getDeviceID());
        System.out.println("Blade Key: " + queriedUser.getMobileKeyString());
        
        if (queriedUser.getDeviceID().equals(param_user_bladeUUID)
        		&& queriedUser.getMobileKeyString().equals(param_user_bladeKey)) {
        	userDao.delete(queriedUser);
        	return "SUCCESS";
        }
        else return ServerConstants.messageFailedToDeleteQuad;
	}
	
}
