package com.sistema.autolaudo.model.permissao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="permissao")
@NoArgsConstructor
@Getter
@Setter
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "nome_permissao", nullable = false, length = 50)
    private String nomePermissao;

    // Relacionamento inverso (opcional)
    @OneToMany(mappedBy = "permissao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermissao> usuarios;
}