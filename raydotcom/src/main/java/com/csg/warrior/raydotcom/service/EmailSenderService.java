package com.csg.warrior.raydotcom.service;

public interface EmailSenderService {
    public void sendMail(String recipient, String subject, String message);
}
