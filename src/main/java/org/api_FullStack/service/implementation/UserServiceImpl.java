package org.api_FullStack.service.implementation;

import org.api_FullStack.model.User;
import org.api_FullStack.service.cript.EncriptaDecriptaRSA;
import org.api_FullStack.repository.UserRepository;
import org.api_FullStack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EncriptaDecriptaRSA encriptaDecriptaRSA;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
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
            novaSenha = cript(userToCreate.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }


        userToCreate.setPassword(novaSenha);
        return userRepository.save(userToCreate);
    }

    private String cript(String senha) throws Exception {
        ObjectInputStream objectInputStream = null;
        String senhaCriptografada;
        try {

            //Criptografando a senha.
            objectInputStream = new ObjectInputStream(new FileInputStream(encriptaDecriptaRSA.PATH_CHAVE_PUBLICA));
            /*
            *Realiza a Leitura do arquivo de chave publica e carrega para a variavel chavePublica.
             */
            final PublicKey chavePublica = (PublicKey) objectInputStream.readObject();
            final byte[] textoCriptografado = encriptaDecriptaRSA.criptografa(senha, chavePublica);
            senhaCriptografada = textoCriptografado.toString();


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return senhaCriptografada;
    }

    private void descript(byte[] criptPassword) throws Exception {
        ObjectInputStream objectInputStream = null;
        try {
            // Decriptografa a Mensagem usando a Chave Pirvada
            objectInputStream = new ObjectInputStream(new FileInputStream(encriptaDecriptaRSA.PATH_CHAVE_PRIVADA));
            /*
             *Realiza a Leitura do arquivo de chave publica e carrega para a variavel chavePublica.
             */
            final PrivateKey chavePrivada = (PrivateKey) objectInputStream.readObject();
            final String textoPuro = encriptaDecriptaRSA.decriptografa(criptPassword, chavePrivada);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
