package org.api_FullStack.service;

import org.api_FullStack.model.LoginRequest;
import org.api_FullStack.model.User;
import org.api_FullStack.repository.UserRepository;
import org.api_FullStack.service.cript.EncriptaDecriptaRSA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthService {


    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final EncriptaDecriptaRSA encriptaDecriptaRSA;

    private final UserRepository userRepository;
    private final UserService userService;

    public AuthService(EncriptaDecriptaRSA encriptaDecriptaRSA, UserRepository userRepository, UserService userService) {
        this.encriptaDecriptaRSA = encriptaDecriptaRSA;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public String login(LoginRequest login) throws AuthenticationException {
        User userLocalizado;
        if(login.getPassword() ==null){
            throw new AuthenticationException("Senha inválida.");
        }
        String userPasswordRequest;
        if (userRepository.existsByEmail(login.getEmail())) {
            userLocalizado = userRepository.findByEmail(login.getEmail());
        }else{
            throw new AuthenticationException("Email inválido.");
        }

        String password;
        try {
            /*
            criptografar a senha que a pessoa passou para validar
            se bate com a senha criptgrafa do usuario salvo no banco.

            password = encriptaDecriptaRSA.cript(login.getPassword());
            */
            String senhaDescript = userLocalizado.getPassword();
            System.out.println(senhaDescript.getBytes());
            password = encriptaDecriptaRSA.descript(senhaDescript.getBytes());
            if(login.getPassword() == password){
                return "token_gerado";
            }else {
                throw new AuthenticationException("Credenciais inválidas!");
            }
        }catch (Exception e){
            throw new AuthenticationException(e.getMessage());
        }
    }
}
