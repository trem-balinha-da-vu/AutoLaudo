package com.sistema.autolaudo.model.selodigital;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "selo_digital")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeloDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSelo;

    @Column
    private java.time.LocalDateTime dataEmissao;

    @Column(length = 255)
    private String autoridadeEmissora;

    @Column(length = 255)
    private String hashConformidade;
}
