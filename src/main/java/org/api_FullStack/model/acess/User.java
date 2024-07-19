package org.api_FullStack.model.acess;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter

@Entity(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String email;
}
