package com.sistema.autolaudo.service.impl.usuario;

import com.sistema.autolaudo.dto.permissao.PermissaoResponse;
import com.sistema.autolaudo.dto.usuario.UsuarioRequest;
import com.sistema.autolaudo.dto.usuario.UsuarioResponse;
import com.sistema.autolaudo.mapper.permissao.PermissaoMapper;
import com.sistema.autolaudo.mapper.usuario.UsuarioMapper;
import com.sistema.autolaudo.model.administrador.Administrador;
import com.sistema.autolaudo.model.gerenteregional.GerenteRegional;
import com.sistema.autolaudo.model.nivelacesso.NivelAcesso;
import com.sistema.autolaudo.model.perfil.Perfil;
import com.sistema.autolaudo.model.perito.Perito;
import com.sistema.autolaudo.model.permissao.Permissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissaoId;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.repository.administrador.AdministradorRepository;
import com.sistema.autolaudo.repository.gerenteregional.GerenteRegionalRepository;
import com.sistema.autolaudo.repository.nivelacesso.NivelAcessoRepository;
import com.sistema.autolaudo.repository.perfil.PerfilRepository;
import com.sistema.autolaudo.repository.perito.PeritoRepository;
import com.sistema.autolaudo.repository.permissao.PermissaoRepository;
import com.sistema.autolaudo.repository.permissao.UsuarioPermissaoRepository;
import com.sistema.autolaudo.repository.usuario.UsuarioRepository;
import com.sistema.autolaudo.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PermissaoMapper permissaoMapper;

    @Autowired
    private PeritoRepository peritoRepository;

    @Autowired
    private GerenteRegionalRepository gerenteRegionalRepository;

    @Autowired
    private AdministradorRepository administradorRepository;

    @Autowired
    private NivelAcessoRepository nivelAcessoRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private UsuarioPermissaoRepository usuarioPermissaoRepository;

    @Override
    @Transactional
    public UsuarioResponse criarUsuario(UsuarioRequest request) {
        // 1. Cria usuario
        Perfil perfil = perfilRepository.findById(request.getPerfilId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenhaHash(request.getSenha());
        usuario.setPerfil(perfil);
        usuario = usuarioRepository.save(usuario);

        // Switch nos perfis
        if (request.getPerfilId() == 1) {
            // PERITO
            Perito perito = new Perito();
            perito.setUsuario(usuario);
            perito.setCrmPerito(request.getCrmPerito());
            perito.setAreaEspecialidade(request.getAreaEspecialidade());
            perito.setNivelExperiencia(request.getNivelExperiencia());
            perito.setTelefoneContato(request.getTelefoneContato());
            perito.setAtivo(true);
            perito.setUuid(UUID.randomUUID().toString());
            peritoRepository.save(perito);

            adicionarPermissoes(usuario, Arrays.asList(1L, 3L, 5L)); // 1, 3, 5

        } else if (request.getPerfilId() == 2) {
            // GERENTE_REGIONAL (também precisa de Perito)
            Perito perito = new Perito();
            perito.setUsuario(usuario);
            perito.setCrmPerito(request.getCrmPerito());
            perito.setAreaEspecialidade(request.getAreaEspecialidade());
            perito.setNivelExperiencia(request.getNivelExperiencia());
            perito.setTelefoneContato(request.getTelefoneContato());
            perito.setAtivo(true);
            perito.setUuid(UUID.randomUUID().toString());
            perito = peritoRepository.save(perito);

            GerenteRegional gerente = new GerenteRegional();
            gerente.setPerito(perito);
            gerente.setRegional(request.getRegional());
            gerente.setUuid(UUID.randomUUID().toString());
            gerenteRegionalRepository.save(gerente);

            // Permissões restantes (2, 4, 6, 7)
            adicionarPermissoes(usuario, Arrays.asList(2L, 4L, 6L, 7L));

        } else if (request.getPerfilId() == 3) {
            // ADMINISTRADOR
            Administrador admin = new Administrador();
            admin.setUsuario(usuario);

            Long nivelId = request.getNivelAcessoId() != null ? request.getNivelAcessoId() : 1L;
            NivelAcesso nivelAcesso = nivelAcessoRepository.findById(nivelId)
                    .orElseThrow(() -> new RuntimeException("Nivel de acesso não encontrado"));
            admin.setNivelAcesso(nivelAcesso);

            admin.setUuid(UUID.randomUUID().toString());
            administradorRepository.save(admin);
        }

        return usuarioMapper.toResponse(usuario);

    }

    @Override
    @Transactional
    public UsuarioResponse atualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Perfil perfil = perfilRepository.findById(request.getPerfilId())
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        usuarioMapper.updateModel(request, usuario, perfil);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    @Transactional
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuarioMapper.toResponse(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> buscarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissaoResponse> listarPermissoesDoUsuario(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Set<UsuarioPermissao> usuarioPermissoes = usuario.getPermissoes();

        return usuarioPermissoes.stream()
                .map(up -> permissaoMapper.toResponse(up.getPermissao()))
                .collect(Collectors.toList());
    }

    private void adicionarPermissoes(Usuario usuario, List<Long> permissaoIds) {
        for (Long idPermissao : permissaoIds) {
            Permissao permissao = permissaoRepository.findById(idPermissao)
                    .orElseThrow(() -> new RuntimeException("Permissão não encontrada: " + idPermissao));

            UsuarioPermissaoId id = new UsuarioPermissaoId(usuario.getId(), permissao.getId());

            UsuarioPermissao up = new UsuarioPermissao();
            up.setId(id);
            up.setUsuario(usuario);
            up.setPermissao(permissao);

            usuarioPermissaoRepository.save(up);
        }
    }
}
