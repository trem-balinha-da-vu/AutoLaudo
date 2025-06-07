package com.sistema.autolaudo.model.permissao;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class UsuarioPermissaoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_usuario", nullable = false)
    private Long usuarioId;

    @Column(name = "id_permissao", nullable = false)
    private Long permissaoId;

    public UsuarioPermissaoId(Long usuarioId, Long permissaoId) {
        this.usuarioId = usuarioId;
        this.permissaoId = permissaoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioPermissaoId)) return false;
        UsuarioPermissaoId that = (UsuarioPermissaoId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
                Objects.equals(permissaoId, that.permissaoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, permissaoId);
    }
}
