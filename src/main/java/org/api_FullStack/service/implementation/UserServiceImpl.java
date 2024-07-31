package org.api_FullStack.service.implementation;

import org.api_FullStack.model.User;
import org.api_FullStack.service.cript.EncriptaDecriptaRSA;
import org.api_FullStack.repository.UserRepository;
import org.api_FullStack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EncriptaDecriptaRSA encriptaDecriptaRSA;

    private final UserRepository userRepository;

    public UserServiceImpl(EncriptaDecriptaRSA encriptaDecriptaRSA, UserRepository userRepository) {
        this.encriptaDecriptaRSA = encriptaDecriptaRSA;
        this.userRepository = userRepository;
    }
    @Override
    public User findById(long id){
        /*if(userRepository.findById(id) != null) {
            User userFound = userRepository.findByEmail(email);
            String passwordCript = userFound.getPassword();
            String passwordDescript;
            try {
                 passwordDescript = encriptaDecriptaRSA.descript(passwordCript);
                 userFound.setPassword(passwordDescript);
            }catch (Exception e){
                e.printStackTrace();
            }
            return userFound;
        }else {
            throw new NoSuchElementException("Usuario não encontrado");
        }*/
        return userRepository.findById(id);
    }

    @Override
    public User create(User userToCreate) {
        if(userRepository.existsByEmail(userToCreate.getEmail())){
            throw new IllegalArgumentException("Usuário já existente, faça o Login.");
        }
        if(userToCreate.getPassword().length() < 6){
            throw new IllegalArgumentException("Senha deve ser maior que 6 caracteres.");
        }
        String novaSenha = "";
        //Carregar a senha passada para a criação do usuario.
        try {
            novaSenha = encriptaDecriptaRSA.cript(userToCreate.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }


        userToCreate.setPassword(novaSenha);
        return userRepository.save(userToCreate);
    }


}
