package com.email.Controllers;

import com.email.Models.User;
import com.email.Services.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;


/**
 * Created by admin on 28/12/2017.
 */
@RestController
public class EmailController {

    @Autowired
    NotificationService notificationService;

    private Logger logger = LoggerFactory.getLogger(EmailController.class);

    @RequestMapping("/mail/send")
    public String index() {
        User user  = new User("Ashish", "Kumar", "sender@gmail.com");
        try{
            notificationService.sendNotification(user);
        }
        catch (MessagingException e){
            logger.info("error sending mail : "+e);
            return "email not sent!!!";
        }
        return "email sent successfully";
    }

}
