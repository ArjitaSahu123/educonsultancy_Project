package com.backend.educonsultancy_backend.service;

import com.backend.educonsultancy_backend.dto.MailBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Value("${spring.mail.username}") // Inject the value of mail.username
    private String fromEmail;

    public void sendSimpleMessage(MailBody mailBody){


        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailBody.to());
        message.setFrom(fromEmail);    //our email
        message.setSubject(mailBody.subject());
        message.setText(mailBody.text());

        javaMailSender.send(message);
    }
}
