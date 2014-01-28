package com.csg.warrior.raydotcom.service;


import org.springframework.mail.MailSender;

public interface EmailSenderService {
    public void setMailSender(MailSender mailSender);
    public void sendMail(String recipient, String subject, String message);
}
