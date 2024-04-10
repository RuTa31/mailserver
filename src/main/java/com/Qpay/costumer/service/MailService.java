package com.Qpay.costumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.Qpay.costumer.repository.MailRepository;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import java.io.File;

@Service
@RequiredArgsConstructor
public class MailService implements MailRepository {
    public static final String NEW_USER_ACCOUNT_VERIFICATION = "Test User Account Verification";
    public static final String UTF_8_ENCODING = "UTF-8";
    public static final String EMAIL_TEMPLATE = "emailtemplate";
    public static final String TEXT_HTML_ENCONDING = "text/html";
    private final JavaMailSender emailSender;
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async
    public void sendSimpleMailMessage(String name, String to, String body) {
        System.out.println("server" + body);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(to);
            message.setText("hello this is my new mail....");
            emailSender.send(message);
            System.out.println("server" + message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    @Async
    public void sendMimeMessageWithAttachments(String name, String to, String body) {
        try {
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setText("--- this file ---", false);
            // Add attachments
            FileSystemResource pdf = new FileSystemResource(
                    new File(
                            "/src/main/java/com/Qpay/costumer/files/keyboard-shortcuts-macos.pdf"));
            // FileSystemResource png = new FileSystemResource(
            // new File(System.getProperty("user.home")
            // + "/src/main/java/com/Qpay/costumer/files/keyboard-shortcuts-macos.pdf"));

            helper.addAttachment("newimage.pdf", pdf);
            // helper.addAttachment(png.getFilename(), png);
            emailSender.send(message);
            System.out.println("server-------file" + message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Override
    public void sendMimeMessageWithEmbeddedFiles(String name, String to, String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendMimeMessageWithEmbeddedFiles'");
    }

    @Override
    public void sendHtmlEmail(String name, String to, String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendHtmlEmail'");
    }

    @Override
    public void sendHtmlEmailWithEmbeddedFiles(String name, String to, String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sendHtmlEmailWithEmbeddedFiles'");
    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

}
