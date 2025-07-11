package com.sistema.autolaudo.model.orgaorequisitante;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orgao_requisitante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrgaoRequisitante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrgao;

    @Column(length = 100, nullable = false, unique = true)
    private String nome;
}
