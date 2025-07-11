package com.sistema.autolaudo.model.laudoveicular;

import com.sistema.autolaudo.model.historicoalteracao.HistoricoAlteracao;
import com.sistema.autolaudo.model.imagem.Imagem;
import com.sistema.autolaudo.model.laudostatus.LaudoStatus;
import com.sistema.autolaudo.model.norma.Norma;
import com.sistema.autolaudo.model.orgaorequisitante.OrgaoRequisitante;
import com.sistema.autolaudo.model.selodigital.SeloDigital;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.model.veiculo.Veiculo;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "laudo_veicular")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaudoVeicular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLaudo;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataEntrega;

    private LocalDateTime dataEmissao;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String resumoProblema;

    @Column(columnDefinition = "TEXT")
    private String detalhamentoAnalises;

    @Column(columnDefinition = "TEXT")
    private String conclusao;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(length = 255)
    private String hashDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_status", nullable = false)
    private LaudoStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_orgao", nullable = false)
    private OrgaoRequisitante orgaoRequisitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_selo")
    private SeloDigital seloDigital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formulario")
    private FormularioLaudo formularioLaudo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placa_veiculo", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perito", nullable = false)
    private Usuario perito;

    // Normas aplicadas (N-N)
    @ManyToMany
    @JoinTable(name = "laudo_norma",
            joinColumns = @JoinColumn(name = "id_laudo"),
            inverseJoinColumns = @JoinColumn(name = "id_norma"))
    private List<Norma> normasAplicadas;

    // Histórico
    @OneToMany(mappedBy = "laudoVeicular", cascade = CascadeType.ALL)
    private List<HistoricoAlteracao> historicoAlteracoes;

    // Imagens
    @OneToMany(mappedBy = "laudoVeicular", cascade = CascadeType.ALL)
    private List<Imagem> imagens;
}
