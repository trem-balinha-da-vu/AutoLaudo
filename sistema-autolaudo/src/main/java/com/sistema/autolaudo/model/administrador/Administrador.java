package com.sistema.autolaudo.model.administrador;

import com.sistema.autolaudo.model.nivelacesso.NivelAcesso;
import com.sistema.autolaudo.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "administrador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_nivel", referencedColumnName = "id_nivel")
    private NivelAcesso nivelAcesso;

    @Column(name = "uuid", nullable = false)
    private String uuid;
}
