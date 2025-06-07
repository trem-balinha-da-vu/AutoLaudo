package com.sistema.autolaudo.model.nivelacesso;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nivel_acesso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NivelAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel")
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;
}
