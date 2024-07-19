package org.api_FullStack.service.implementation;

import org.api_FullStack.model.User;
import org.api_FullStack.repository.UserRepository;
import org.api_FullStack.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

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
        return userRepository.save(userToCreate);
    }
}
