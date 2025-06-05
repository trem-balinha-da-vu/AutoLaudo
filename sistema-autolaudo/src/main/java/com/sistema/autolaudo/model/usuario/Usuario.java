package com.sistema.autolaudo.model.usuario;

import com.sistema.autolaudo.model.common.BaseModel;
import com.sistema.autolaudo.model.perfil.Perfil;
import com.sistema.autolaudo.model.permissao.UsuarioPermissao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="usuario")
@NoArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends BaseModel<Usuario> {

    private static final long serialVersionUID = 8279141167808009830L;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senhaHash;

    // Relacionamento N:N se usar permissões (opcional)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioPermissao> permissoes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    private Perfil perfil;

}
