package com.sistema.autolaudo.model.norma;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "norma")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Norma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNorma;

    @Column(nullable = false, length = 255)
    private String nomeNorma;

    @Column(length = 20)
    private String tipo; // CONTRAN, ICPAS, etc.

    @Column(columnDefinition = "TEXT")
    private String textoNorma;
}
