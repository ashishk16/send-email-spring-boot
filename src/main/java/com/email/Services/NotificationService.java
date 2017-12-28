package com.email.Services;

import com.email.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;
    @Value("{spring.mail.username}")
    private String email;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
     }

     public void sendNotification(User user) throws MailException{
         SimpleMailMessage mail = new SimpleMailMessage();
         mail.setTo(user.getEmail());
         mail.setFrom(email);
         mail.setSubject("Testing...");
         mail.setText("Attack is the best defence");
         javaMailSender.send(mail);
     }
}
