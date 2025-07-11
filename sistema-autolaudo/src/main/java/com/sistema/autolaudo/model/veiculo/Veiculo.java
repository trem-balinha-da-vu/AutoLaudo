package com.sistema.autolaudo.model.veiculo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "veiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Veiculo {

    @Id
    @Column(length = 20)
    private String placa;

    @Column(length = 30)
    private String chassi;

    @Column(length = 30)
    private String renavam;

    @Column(length = 100)
    private String modelo;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Column(name = "status_registro", length = 20)
    private String statusRegistro;
}
