package com.csg.warrior.raydotcom.service.impl;

import com.csg.warrior.raydotcom.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired private MailSender mailSender;

    @Override
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(String recipient, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ray.com");
        message.setTo(recipient);
        message.setSubject("Unsubscribe Mobile key");
        message.setText("ang pangit mo");
        mailSender.send(message);
        mailSender.send(message);
    }
}
