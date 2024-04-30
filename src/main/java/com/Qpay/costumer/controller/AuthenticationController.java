package com.Qpay.costumer.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Qpay.costumer.dto.AuthenticationRequest;
import com.Qpay.costumer.dto.AuthenticationResponse;
import com.Qpay.costumer.dto.MeRequest;
import com.Qpay.costumer.dto.RegisterRequest;
import com.Qpay.costumer.model.User;
import com.Qpay.costumer.service.AuthenticationService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    // @PostMapping("/me")
    // public ResponseEntity<ResponseEntity<User>> authMe(
    // @RequestBody MeRequest request) {
    // return ResponseEntity.ok(service.authMe(request));
    // }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }
}
