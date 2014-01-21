package com.csg.warrior.service;

public interface KeyRequestService {
	
	/*
	 * pero tulad nga ng sabi ni Smit, pwede nang (username, website) since hindi magkakaron ng anomalous data na kung saan
	 * may isang (username, website) pair na mapped sa dalawang gcm_device_id. pero para safe na rin, konting overhead lang naman
	 */
	
	void checkWarriorRegistration(String username, String website, String gcm_device_id);
	
}
