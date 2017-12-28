package com.email.Services;

import com.email.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;
    @Value("{spring.mail.username}")
    private String email;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
     }

     public void sendNotification(User user) throws MessagingException {
         MimeMessage message = javaMailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message, true);
         helper.setTo(user.getEmail());
         helper.setSubject("Testing MimeMessage...");
         helper.setText("<h3>Attack is the best defence<h3><br/><h6>Regards,</h6><br/><h5>Ashish Kumar<h5>", true);
         FileSystemResource file = new FileSystemResource(new File("/Users/admin/Desktop/3brothers.jpg"));
         helper.addAttachment("Attachment", file);
         javaMailSender.send(message);
     }
}
