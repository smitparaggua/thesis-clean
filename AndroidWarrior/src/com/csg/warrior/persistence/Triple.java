package com.csg.warrior.persistence;

public class Triple {
	int id;
	String username;
	String url;
	String key;
	
	//empty constructor

	
	public Triple(String p_username, String p_url, String p_key) {
		this.username = p_username;
		this.url = p_url;		
		this.key = p_key;
	}
	
	//getters
	public String getUsername() {
		return username;
	}
	
	public String getURL() {
		return url;
	}
	
	public String getKey() {
		return key;
	}
	
	//setters
	public void setUsername(String p_username) {
		this.username = p_username;
	}
	
	public void setURL(String p_url) {
		this.url = p_url;
	}
	
	public void setKey(String p_key) {
		this.key = p_key;
	}
	
	
}
