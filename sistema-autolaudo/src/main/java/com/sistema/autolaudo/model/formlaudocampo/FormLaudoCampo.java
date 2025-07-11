package com.sistema.autolaudo.model.formlaudocampo;


import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "form_laudo_campo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class FormLaudoCampo {

    @EmbeddedId
    private FormLaudoCampoId id;

    @Column(columnDefinition = "TEXT")
    private String valor;



    @MapsId("idFormulario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formulario")
    private FormularioLaudo formularioLaudo;
}
