package com.email.IntegrationTests;

import com.email.Models.User;
import com.email.Services.NotificationService;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Message;
import javax.mail.MessagingException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailIntegrationTest {
    @Autowired
    NotificationService notificationService;
    private GreenMail testSmtp;

    @Before
    public void testSmtpInit(){
        testSmtp = new GreenMail(new ServerSetup(3025, null, "smtp"));
        testSmtp.start();
    }

    @Test
    public void should_compare_content_of_mail_send_and_received() throws MessagingException, IOException {
        //Act
        notificationService.sendNotification(new User("ashish","kumar","ak@gmail.com"));

        //Assert
        Message[] messages = testSmtp.getReceivedMessages();
        assertEquals(1, messages.length);
        assertEquals("Testing MimeMessage...", messages[0].getSubject());
        assertEquals("ak@gmail.com", messages[0].getAllRecipients()[0].toString());
    }

    @After
    public void cleanup(){
        testSmtp.stop();
    }
}
