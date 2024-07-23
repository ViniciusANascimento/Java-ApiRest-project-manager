package org.api_FullStack.controller;

import org.api_FullStack.model.AuthResponse;
import org.api_FullStack.model.LoginRequest;
import org.api_FullStack.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@CrossOrigin(origins = "http:127.0.0.1:5500")
@RestController
@RequestMapping("/auth")
public class LoginController {


    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/{login}")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            var token = authService.login(loginRequest);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Informações de Login inválidas.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno.");
        }
    }
}


