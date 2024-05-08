package com.example.fabonuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    
    private static final String FROM_EMAIL = "kittyvik06@gmail.com";

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendPasswordChangeMessage(String to) {
    	
    	// Construct subject of email
    	String subject = "Password change - FabOn";
    	
        // Construct the dynamic link URL
        String dynamicLink = "http://localhost:8080/user/changePasswordEmail";

        // Construct the message body with the dynamic link
        String changePasswordMessageBody = "Dear User,\n\n" +
                "Thank you for using our service. To change your password, please click the link below:\n\n" +
                dynamicLink + "\n\n" +
                "If you did not request a password change, please ignore this email.\n\n" +
                "Best regards,\n" +
                "The Fabon Team";

        // Create a SimpleMailMessage object
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(changePasswordMessageBody);

        // Send the email
        emailSender.send(message);
    }
}