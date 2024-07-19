package org.api_FullStack.service;

import org.api_FullStack.model.acess.User;

public interface UserService {

    User findById(Long id);

    User create(User userToCreate);
}
