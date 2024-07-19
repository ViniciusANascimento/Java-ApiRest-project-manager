package org.api_FullStack.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.api_FullStack.model.acess.User;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "tb_agenda")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private String descricao;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("tb_user.id" = userCode)
    private int userCode;

}
