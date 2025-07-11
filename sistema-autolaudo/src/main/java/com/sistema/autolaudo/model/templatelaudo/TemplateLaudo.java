package com.sistema.autolaudo.model.templatelaudo;

import com.sistema.autolaudo.model.norma.Norma;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "template_laudo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateLaudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemplate;

    private String nome;
    private String versao;

    @ElementCollection
    private List<String> camposObrigatorios;

    @ManyToMany
    @JoinTable(name = "template_norma",
            joinColumns = @JoinColumn(name = "id_template"),
            inverseJoinColumns = @JoinColumn(name = "id_norma"))
    private List<Norma> normasAssociadas;
}
