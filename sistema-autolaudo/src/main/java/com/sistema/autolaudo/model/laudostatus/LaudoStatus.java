package com.sistema.autolaudo.model.laudostatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "laudo_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaudoStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    @Column(length = 50, nullable = false, unique = true)
    private String nome; // AGUARDANDO_AVALIACAO, APROVADO, etc.
}
