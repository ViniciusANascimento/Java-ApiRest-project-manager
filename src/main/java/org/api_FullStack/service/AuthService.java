package org.api_FullStack.service;

import org.api_FullStack.model.LoginRequest;
import org.api_FullStack.model.User;
import org.api_FullStack.repository.UserRepository;
import org.api_FullStack.service.cript.EncriptaDecriptaRSA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.naming.AuthenticationException;

@Service
public class AuthService {


    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final EncriptaDecriptaRSA encriptaDecriptaRSA;

    private final UserRepository userRepository;
    private final UserService userService;
    private final ResourceUrlProvider mvcResourceUrlProvider;

    public AuthService(EncriptaDecriptaRSA encriptaDecriptaRSA, UserRepository userRepository, UserService userService, ResourceUrlProvider mvcResourceUrlProvider) {
        this.encriptaDecriptaRSA = encriptaDecriptaRSA;
        this.userRepository = userRepository;
        this.userService = userService;
        this.mvcResourceUrlProvider = mvcResourceUrlProvider;
    }

    /**
    * Metodo de realizar login
    * Entrar uma Entidade LoginRequest
    * Retorno de Acesso liberado ou não.
     * @param login  Entidade Login
    * */
    public String login(LoginRequest login) throws AuthenticationException {

        if(login.getPassword() ==null || login.getPassword().equals("")){
            throw new AuthenticationException("Senha inválida.");
        }
        User userPasswordRequest = verificarCadastrado(login.getEmail());

        String password;
        try {
            /*
            criptografar a senha que a pessoa passou para validar
            se bate com a senha criptgrafa do usuario salvo no banco.

            password = encriptaDecriptaRSA.cript(login.getPassword());
            */
            String senhaDescript = userPasswordRequest.getPassword();
            password = encriptaDecriptaRSA.descript(senhaDescript);
            System.out.println(password);
            System.out.println(login.getPassword());
            if(login.getPassword().equals(password)){
                return "Acesso Liberadoo";
            }else {
                throw new AuthenticationException("Credenciais inválidas!");
            }
        }catch (Exception e){
            throw new AuthenticationException(e.getMessage());
        }
    }
    public String recoveryPassword(String email) throws AuthenticationException {
        try {
            User userRecovery = verificarCadastrado(email);
            String password = encriptaDecriptaRSA.descript(userRecovery.getPassword());;
            return password;
        }catch (AuthenticationException e){
            throw new AuthenticationException(e.getMessage());
        }catch (Exception e){
            throw new AuthenticationException(e.getMessage());
        }

    }

    private User verificarCadastrado (String email) throws AuthenticationException {
        User userLocalizado;
        if (!userRepository.existsByEmail(email)){
            throw new AuthenticationException("Usuário não localizado.");
        }else{
            userLocalizado = userRepository.findByEmail(email);
        }
        return userLocalizado;
    }

}
