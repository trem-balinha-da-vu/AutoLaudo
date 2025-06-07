package com.sistema.autolaudo.model.perito;

import com.sistema.autolaudo.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perito")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Perito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "crm_perito", nullable = false)
    private String crmPerito;

    @Column(name = "area_especialidade", nullable = false)
    private String areaEspecialidade;

    @Column(name = "nivel_experiencia")
    private String nivelExperiencia;

    @Column(name = "telefone_contato")
    private String telefoneContato;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "uuid", nullable = false)
    private String uuid;
}
