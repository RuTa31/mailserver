package com.Qpay.costumer.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Qpay.costumer.model.EmailRequest;
import com.Qpay.costumer.service.MailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/simple_mail")
    public ResponseEntity<?> mail(@RequestBody EmailRequest request) {
        System.out.println("Post___________");
        mailService.sendSimpleMailMessage(request.getName(), request.getTo(), request.getBody());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/file")
    public ResponseEntity<?> mailAttachFile(@RequestBody EmailRequest request) {
        System.out.println("Post___________");
        mailService.sendMimeMessageWithAttachments(request.getName(), request.getTo(), request.getBody());
        return ResponseEntity.ok().build();
    }
}
