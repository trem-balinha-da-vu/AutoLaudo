package com.sistema.autolaudo.model.formulariolaudo;

import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampo;
import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampoId;
import com.sistema.autolaudo.model.templatelaudo.TemplateLaudo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formulario_laudo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FormularioLaudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormulario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_template")
    private TemplateLaudo template;

    @OneToMany(mappedBy = "formularioLaudo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormLaudoCampo> campos = new ArrayList<>();

    private Boolean modoOffline; // true = preenchido offline

    // Métodos utilitários para auto-complete/auto-preenchimento
    public void preencherCampo(String nome, String valor) {
        if (campos == null) {
            campos = new ArrayList<>();
        }
        // Procura se o campo já existe (busca pelo nome no id composto)
        for (FormLaudoCampo campo : campos) {
            if (campo.getId().getNomeCampo().equals(nome)) {
                campo.setValor(valor);
                return;
            }
        }
        // Se não existe, cria novo campo
        FormLaudoCampo novo = FormLaudoCampo.builder()
                .id(new FormLaudoCampoId(this.getIdFormulario(), nome))
                .valor(valor)
                .formularioLaudo(this)
                .build();
        campos.add(novo);
    }
}
