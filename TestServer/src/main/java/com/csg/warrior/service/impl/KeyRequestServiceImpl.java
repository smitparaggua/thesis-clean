package com.csg.warrior.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.warrior.KeyStringGenerator;
import com.csg.warrior.ServerConstants;
import com.csg.warrior.dao.UserMobileKeyDao;
import com.csg.warrior.domain.MobileKey;
import com.csg.warrior.domain.User;
import com.csg.warrior.domain.UserMobileKey;
import com.csg.warrior.service.KeyRequestService;
import com.csg.warrior.service.UserMobileKeyService;
import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@Service
@Transactional
public class KeyRequestServiceImpl implements KeyRequestService {
	@Autowired private UserMobileKeyDao userMobileKeyDao;
	@Autowired private UserMobileKeyService userMobileKeyService;
	
	@Override
	public void checkWarriorRegistration(String username, 
									String website, 
									String gcmDeviceID) {
		
		String hql = "FROM User WHERE User.username := username AND User.website := website AND User.gcmDeviceID := gcmDeviceID";
		
		//String hql = "FROM User WHERE username = \'" + username + "\'" +
			//			"AND website = \'" + website + "\'" +
				//		"AND gcmDeviceID = \'" + gcmDeviceID + "\'";
		
		Map<String, Object> queryParameters = new HashMap<String, Object>();
		queryParameters.put("username", username);
		queryParameters.put("website", website);
		queryParameters.put("gcmDeviceID", gcmDeviceID);
		
		UserMobileKey userMobileKey = userMobileKeyDao.queryUniqueResult(hql, queryParameters);
		
		if(userMobileKey == null) { 
			User user = new User(username, website, gcmDeviceID);
			MobileKey mobilekey = new MobileKey(KeyStringGenerator.generateKeyString());
			userMobileKeyService.save(user, mobilekey);
			
			gcm_sendWARKey(username, website, user.getGcmDeviceID(), mobilekey.getKeyString());
		}
		else if(userMobileKey != null){
			String mobileKeyString = userMobileKey.getMobileKey().getKeyString();
			System.out.println("This device already WAR registered. \n" +
								"Username: " + username +
								"Website: " + website +
								"GCM Device ID: " + gcmDeviceID +
								"WAR key: " + mobileKeyString);

			gcm_sendWARKey(username, website, gcmDeviceID, mobileKeyString);
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
