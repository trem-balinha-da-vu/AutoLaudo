package com.sistema.autolaudo.service.impl.perito;

import com.sistema.autolaudo.dto.gerenteregional.GerenteRegionalResponse;
import com.sistema.autolaudo.dto.perito.PeritoResponse;
import com.sistema.autolaudo.mapper.gerenteregional.GerenteRegionalMapper;
import com.sistema.autolaudo.mapper.perito.PeritoMapper;
import com.sistema.autolaudo.model.gerenteregional.GerenteRegional;
import com.sistema.autolaudo.model.perfil.Perfil;
import com.sistema.autolaudo.model.perito.Perito;
import com.sistema.autolaudo.model.permissao.Permissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissaoId;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.repository.gerenteregional.GerenteRegionalRepository;
import com.sistema.autolaudo.repository.perfil.PerfilRepository;
import com.sistema.autolaudo.repository.perito.PeritoRepository;
import com.sistema.autolaudo.repository.permissao.PermissaoRepository;
import com.sistema.autolaudo.repository.permissao.UsuarioPermissaoRepository;
import com.sistema.autolaudo.repository.usuario.UsuarioRepository;
import com.sistema.autolaudo.service.perito.PeritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class PeritoServiceImpl implements PeritoService {

    @Autowired
    private PeritoRepository peritoRepository;
    @Autowired
    private GerenteRegionalRepository gerenteRegionalRepository;
    @Autowired
    private UsuarioPermissaoRepository usuarioPermissaoRepository;
    @Autowired
    private PermissaoRepository permissaoRepository;
    @Autowired
    private GerenteRegionalMapper gerenteRegionalMapper;
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PeritoMapper peritoMapper;

    // IDs das permissões do gerente e do perito (ajuste se for diferente)
    private static final List<Long> PERMISSOES_GERENTE = Arrays.asList(2L, 4L, 6L, 7L);
    private static final List<Long> PERMISSOES_PERITO = Arrays.asList(1L, 3L, 5L);

    @Override
    @Transactional
    public GerenteRegionalResponse promoverParaGerente(Long peritoId, String regional) {
        Perito perito = peritoRepository.findById(peritoId)
                .orElseThrow(() -> new RuntimeException("Perito não encontrado"));

        Usuario usuario = perito.getUsuario();

        usuario.setPerfil(perfilRepository.findById(2L)
                .orElseGet(() -> {
                        Perfil novo = new Perfil();
                        novo.setPerfil("GERENTE_REGIONAL");
                        novo.setId_perfil(2L);
                        return novo;
                    })
        );

        // Cria registro gerente_regional se não existir
        GerenteRegional gerente = gerenteRegionalRepository.findByPeritoId(peritoId)
                .orElseGet(() -> {
                    GerenteRegional novo = new GerenteRegional();
                    novo.setPerito(perito);
                    novo.setRegional(regional);
                    novo.setUuid(UUID.randomUUID().toString());
                    return gerenteRegionalRepository.save(novo);
                });

        for (Long idPermissao : PERMISSOES_GERENTE) {
            UsuarioPermissaoId upId = new UsuarioPermissaoId(usuario.getId(), idPermissao);
            if (!usuarioPermissaoRepository.existsById(upId)) {
                Permissao permissao = permissaoRepository.findById(idPermissao)
                        .orElseThrow(() -> new RuntimeException("Permissão não encontrada: " + idPermissao));
                UsuarioPermissao up = new UsuarioPermissao();
                up.setId(upId);
                up.setUsuario(usuario);
                up.setPermissao(permissao);
                usuarioPermissaoRepository.save(up);
            }
        }

        return gerenteRegionalMapper.toResponse(gerente);
    }

    @Override
    @Transactional
    public PeritoResponse rebaixarParaPerito(Long peritoId) {
        // Remove gerente_regional se existir
        gerenteRegionalRepository.deleteByPeritoId(peritoId);

        Perito perito = peritoRepository.findById(peritoId)
                .orElseThrow(() -> new RuntimeException("Perito não encontrado"));
        Usuario usuario = perito.getUsuario();

        usuario.setPerfil(perfilRepository.findById(1L)
                .orElseGet(() -> {
                    Perfil novo = new Perfil();
                    novo.setPerfil("PERITO");
                    novo.setId_perfil(1L);
                    return novo;
                })
        );

        // Remove permissões de gerente, mantém só as de perito
        for (Long idPermissao : PERMISSOES_GERENTE) {
            UsuarioPermissaoId upId = new UsuarioPermissaoId(usuario.getId(), idPermissao);
            if (usuarioPermissaoRepository.existsById(upId)) {
                usuarioPermissaoRepository.deleteById(upId);
            }
        }

        // 1. Recupera todas as permissões do usuário
        List<UsuarioPermissao> permissoesUsuario = usuarioPermissaoRepository.findAllByUsuarioId(usuario.getId());

        // 2. Para cada permissão do usuário, remove se NÃO for permissão de perito
        for (UsuarioPermissao up : permissoesUsuario) {
            Long idPermissao = up.getPermissao().getId();
            if (!PERMISSOES_PERITO.contains(idPermissao)) {
                usuarioPermissaoRepository.delete(up);
            }
        }

        // 3. Garante que o usuário terá todas as permissões de perito (cria se faltar)
        for (Long idPermissao : PERMISSOES_PERITO) {
            UsuarioPermissaoId upId = new UsuarioPermissaoId(usuario.getId(), idPermissao);
            if (!usuarioPermissaoRepository.existsById(upId)) {
                Permissao permissao = permissaoRepository.findById(idPermissao)
                        .orElseThrow(() -> new RuntimeException("Permissão não encontrada: " + idPermissao));
                UsuarioPermissao up = new UsuarioPermissao();
                up.setId(upId);
                up.setUsuario(usuario);
                up.setPermissao(permissao);
                usuarioPermissaoRepository.save(up);
            }
        }

        PeritoResponse peritoResponse = peritoMapper.toResponse(perito);

        return peritoResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public PeritoResponse getById(Long id) {
        Perito perito = peritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perito não encontrado"));
        return peritoMapper.toResponse(perito);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PeritoResponse> getAll() {
        return peritoRepository.findAll()
                .stream()
                .map(peritoMapper::toResponse)
                .toList();
    }

}
