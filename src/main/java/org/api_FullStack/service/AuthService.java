package org.api_FullStack.service;

import org.api_FullStack.model.User;
import org.api_FullStack.repository.UserRepository;
import org.api_FullStack.service.cript.EncriptaDecriptaRSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthService {


    private final EncriptaDecriptaRSA encriptaDecriptaRSA;

    private final UserRepository userRepository;

    public AuthService(EncriptaDecriptaRSA encriptaDecriptaRSA, UserRepository userRepository) {
        this.encriptaDecriptaRSA = encriptaDecriptaRSA;
        this.userRepository = userRepository;
    }


    public String login(String emailResponse, String passwordResponse) throws AuthenticationException {
        if(passwordResponse ==null){
            throw new AuthenticationException("Senha inválida.");
        }
        String userPassword = "";

        if (userRepository.existsByEmail(emailResponse)) {

            //userPassword =
        }else{
            throw new AuthenticationException("Email inválido.");
        }

        String password;
        try {
            password = encriptaDecriptaRSA.cript(passwordResponse);
            if(password == userPassword){
                return "token_gerado";
            }else {
                throw new AuthenticationException("Credenciais inválidas!");
            }
        }catch (Exception e){
            throw new AuthenticationException(e.getMessage());
        }
    }
}
