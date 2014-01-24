package com.csg.warrior.service.impl;

import com.csg.warrior.KeyStringGenerator;
import com.csg.warrior.ServerConstants;
import com.csg.warrior.dao.UserDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.KeyRequestService;
import com.csg.warrior.service.UserMobileKeyService;
import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KeyRequestServiceImpl implements KeyRequestService {
    @Autowired private UserDao userDao;
	@Autowired private UserMobileKeyService userMobileKeyService;
	
	@Override
	public void checkWarriorRegistration(User user) {
        user = userDao.getUser(user.getUsername(), user.getWebsite());
		if(user == null) {
			user = new User(user.getUsername(), user.getWebsite(), user.getGcmDeviceID());
			MobileKey mobilekey = new MobileKey(KeyStringGenerator.generateKeyString());
			userMobileKeyService.save(user, mobilekey);
			gcm_sendWARKey(user, mobilekey);
		}
		else {
            MobileKey mobileKey = userMobileKeyService.getMobileKeyOfUser(user);
			System.out.println("This device already WAR registered. \n" +
								"Username: " + user.getUsername() +
								"Website: " + user.getWebsite() +
								"GCM Device ID: " + user.getGcmDeviceID() +
								"WAR key: " + mobileKey.getKeyString());
			gcm_sendWARKey(user, mobileKey);
		}

	}
	
	private void gcm_sendWARKey(User user, MobileKey mobileKey) {
		try {
			Sender sender = new Sender(ServerConstants.GCM_API_KEY);
			Message message = new Message.Builder()
				.addData("username", user.getUsername())
				.addData("website", user.getWebsite())
				.addData("warriorkey", mobileKey.getKeyString())
				.build();
			Result result = sender.send(message, user.getGcmDeviceID(), 10);
			System.out.println(result.toString());			
		}
		catch (InvalidRequestException e){
			System.out.println("Exception class:" + e.getClass());
			System.out.println("Description:" + e.getDescription());
			System.out.println("HTTP status code:" + e.getHttpStatusCode());
		}
		catch (Exception e) {
			System.out.println("Exception class:" + e.getClass());
		}		
	}
	

}
