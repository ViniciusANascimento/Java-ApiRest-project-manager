package org.api_FullStack.service;

import org.api_FullStack.model.User;

public interface UserService {

    User findById(long id);

    User create(User userToCreate);
}
