package com.sistema.autolaudo.model.integracao_detran;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "integracao_detran")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntegracaoDetran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIntegracao;

    @Column(length = 255)
    private String api; // Ex: "busca_veiculo_por_chassi"

    @Column
    private java.time.LocalDateTime dataChamada;

    @Column(columnDefinition = "TEXT")
    private String resultado; // JSON de resposta

    @Column(length = 20)
    private String placaVeiculo; // FK para veiculo (mas só salva placa, não referencia a entidade)
}
