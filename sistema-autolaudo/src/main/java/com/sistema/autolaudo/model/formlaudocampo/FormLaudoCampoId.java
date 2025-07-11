package com.sistema.autolaudo.model.formlaudocampo;


import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormLaudoCampoId implements Serializable {

    private Long idFormulario;

    private String nomeCampo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormLaudoCampoId)) return false;
        FormLaudoCampoId that = (FormLaudoCampoId) o;
        return Objects.equals(idFormulario, that.idFormulario) && Objects.equals(nomeCampo, that.nomeCampo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFormulario, nomeCampo);
    }
}
