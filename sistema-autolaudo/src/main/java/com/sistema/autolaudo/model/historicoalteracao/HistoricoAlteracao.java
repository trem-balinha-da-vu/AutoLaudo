package com.sistema.autolaudo.model.historicoalteracao;

import com.sistema.autolaudo.model.laudoveicular.LaudoVeicular;
import com.sistema.autolaudo.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_alteracao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoAlteracao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHist;

    @Column(nullable = false)
    private LocalDateTime dataAlteracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_laudo")
    private LaudoVeicular laudoVeicular;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;
}
