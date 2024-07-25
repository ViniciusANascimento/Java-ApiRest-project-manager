package org.api_FullStack.controller;

import org.api_FullStack.model.LoginRequest;
import org.api_FullStack.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class LoginController {


    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
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
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/{emailRecovery}")
    public ResponseEntity<?> recovery(@PathVariable String emailRecovery) {
        try {
            var userRecovery = authService.recoveryPassword(emailRecovery);
            return new ResponseEntity<>(userRecovery, HttpStatus.OK);
        }catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não localizado.");
        }

    }
}


