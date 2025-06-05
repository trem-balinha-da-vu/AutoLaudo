package com.sistema.autolaudo.model.permissao;

import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.model.usuario.UsuarioPermissaoId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario_permissao")
@NoArgsConstructor
@Getter
@Setter
public class UsuarioPermissao {

    @EmbeddedId
    private UsuarioPermissaoId id;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario",          // nome da coluna em usuario_permissao
            referencedColumnName = "id")  // aponta para usuario.id (PK da tabela usuario)
    private Usuario usuario;

    @MapsId("permissaoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_permissao", referencedColumnName = "id")
    private Permissao permissao;

    public UsuarioPermissao(Usuario usuario, Permissao permissao) {
        this.usuario   = usuario;
        this.permissao = permissao;
        // Observe: chama-se getId() (já que nossa entidade Permissao define `private Long id;`).
        this.id = new UsuarioPermissaoId(
                usuario.getId(),       // “id” da entidade Usuario
                permissao.getId()      // “id” da entidade Permissao
        );
    }


}
