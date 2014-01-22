package com.csg.warrior.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.csg.warrior.KeyStringGenerator;
import com.csg.warrior.ServerConstants;
import com.csg.warrior.dao.UserMobileKeyDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.service.KeyRequestService;
import com.csg.warrior.service.UserMobileKeyService;
import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class KeyRequestServiceImpl implements KeyRequestService {
	@Autowired private UserMobileKeyDao userMobileKeyDao;
	@Autowired private UserMobileKeyService userMobileKeyService;
	
	@Override
	public void checkWarriorRegistration(String username, 
									String website, 
									String gcm_device_id) {
		
		String hql = "FROM User WHERE username := username AND website := website AND gcmDeviceID := gcm_device_id";
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("username", username);
		queryParameters.put("website", website);
		queryParameters.put("gcmDeviceID", gcm_device_id);
		
		//if not registered
		if(userMobileKeyDao.queryUniqueResult(hql, queryParameters) == null) { 
			User user = new User(username, website, gcm_device_id);
			MobileKey mobilekey = new MobileKey(KeyStringGenerator.generateKeyString());
			userMobileKeyService.save(user, mobilekey);
			
			gcm_sendWARKey(username, website, user.getGcmDeviceID(), mobilekey.getKeyString());
		}
		
		
		
	}
	
	private void gcm_sendWARKey(String username, String website, String target_gcm_device_id, String warriorkey) {
		try {
			Sender sender = new Sender(ServerConstants.GCM_API_KEY);
			Message message = new Message.Builder()
				.addData("username", username)
				.addData("website", website)
				.addData("warriorkey", warriorkey)
				.build();
			Result result = sender.send(message, target_gcm_device_id, 10);
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
