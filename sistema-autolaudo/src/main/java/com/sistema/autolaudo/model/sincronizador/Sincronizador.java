package com.sistema.autolaudo.model.sincronizador;

import com.sistema.autolaudo.model.laudoveicular.LaudoVeicular;
import  com.sistema.autolaudo.model.usuario.Usuario;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "sincronizador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sincronizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSync;

    private LocalDateTime dataSync;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_laudo")
    private LaudoVeicular laudoVeicular;
}

