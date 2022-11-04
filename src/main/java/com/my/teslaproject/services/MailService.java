package com.my.teslaproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send (String fromAddress, String toAddress, String subject, String content) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(fromAddress);
        mimeMessageHelper.setTo(toAddress);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        mimeMessageHelper.setSentDate(new Date());

        javaMailSender.send(mimeMessage);
        System.out.println("Mail is sending...");
    }

}