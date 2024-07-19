package org.api_FullStack.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "tb_agenda")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //Carregar o codigo do usuario que vai estar logado no sistema.
    private long userID;
    private String titulo;
    private String descricao;
    private LocalDate date;

}
