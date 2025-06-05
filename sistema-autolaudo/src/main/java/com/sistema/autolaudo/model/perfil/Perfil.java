package com.sistema.autolaudo.model.perfil;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="perfil")
@NoArgsConstructor
@Getter
@Setter
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id_perfil")
    private Long id_perfil;

    @Column(nullable = false, name = "nome_perfil")
    private String perfil;
}