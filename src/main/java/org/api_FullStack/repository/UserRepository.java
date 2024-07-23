package org.api_FullStack.repository;

import org.api_FullStack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String userEmail);
    User findByEmail(String email);

}
