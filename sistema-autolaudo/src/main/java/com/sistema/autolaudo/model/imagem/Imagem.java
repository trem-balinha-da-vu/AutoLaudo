package com.sistema.autolaudo.model.imagem;

import com.sistema.autolaudo.model.laudoveicular.LaudoVeicular;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "imagem")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagem;

    @Column(length = 255)
    private String caminho;

    @Column(length = 255)
    private String legenda;

    @Column(length = 50)
    private String tipo;

    // Relação direta com LaudoVeicular
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_laudo")
    private LaudoVeicular laudoVeicular;
}
