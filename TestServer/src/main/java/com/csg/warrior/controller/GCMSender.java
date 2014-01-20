package com.csg.warrior.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@Controller
public class GCMSender {
	
	final static String API_KEY = "AIzaSyC5p-qYxnxcGM4MOTyjI3zGxM4GpPmhLJo";
	final static String TEST_TARGET = "APA91bEFG_TmtG_iFlNJ842Y8uaonnoGD29zKor7rykQg0D6XSlTXzLbWAZdvjisgJOTtHpkJ9J5hT6Mzmr3xMIwKwHoOim8tdLJP_xRnsjjtsswP05CwGbripGkeFWPTxiWS8wXbDD_o4x_4B8ATbZeBzy-y_r_VQ";
	
	@RequestMapping("/gcmtest")
	public String send() {
		
		System.out.println("Eclipse4!");
		
		try {
			Sender sender = new Sender(API_KEY);
			Message message = new Message.Builder()
				.build();
			Result result = sender.send(message,TEST_TARGET, 1);
			System.out.println(result.toString());			
		}
		catch (InvalidRequestException e){
			System.out.println("Exception class:" + e.getClass());
			System.out.println("Description:" + e.getDescription());
			System.out.println("HTTP status code:" + e.getHttpStatusCode());
		}
		catch (Exception e) {
			System.out.println("Exception class:" + e.getClass());

			//todo: next time na exception handling hahahaha
		}		
		
		return "gcmdryrun";
	}
}