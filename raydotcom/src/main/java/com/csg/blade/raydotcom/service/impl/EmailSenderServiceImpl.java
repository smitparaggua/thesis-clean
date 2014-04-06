package com.csg.blade.raydotcom.service.impl;

import com.csg.blade.raydotcom.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired private MailSender mailSender;

    @Override
    public void sendMail(String recipient, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ray.com");
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
