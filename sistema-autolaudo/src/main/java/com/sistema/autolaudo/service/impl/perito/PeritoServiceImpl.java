package com.sistema.autolaudo.service.impl.perito;

import com.sistema.autolaudo.dto.gerenteregional.GerenteRegionalResponse;
import com.sistema.autolaudo.dto.perito.PeritoResponse;
import com.sistema.autolaudo.mapper.gerenteregional.GerenteRegionalMapper;
import com.sistema.autolaudo.mapper.perito.PeritoMapper;
import com.sistema.autolaudo.model.gerenteregional.GerenteRegional;
import com.sistema.autolaudo.model.perito.Perito;
import com.sistema.autolaudo.model.permissao.Permissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissaoId;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.repository.gerenteregional.GerenteRegionalRepository;
import com.sistema.autolaudo.repository.perito.PeritoRepository;
import com.sistema.autolaudo.repository.permissao.PermissaoRepository;
import com.sistema.autolaudo.repository.permissao.UsuarioPermissaoRepository;
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
    private PeritoMapper peritoMapper;

    // IDs das permissões do gerente e do perito (ajuste se for diferente)
    private static final List<Long> PERMISSOES_GERENTE = Arrays.asList(2L, 4L, 6L, 7L);
    private static final List<Long> PERMISSOES_PERITO = Arrays.asList(1L, 3L, 5L);

    @Override
    @Transactional
    public GerenteRegionalResponse promoverParaGerente(Long peritoId, String regional) {
        Perito perito = peritoRepository.findById(peritoId)
                .orElseThrow(() -> new RuntimeException("Perito não encontrado"));

        // Cria registro gerente_regional se não existir
        GerenteRegional gerente = gerenteRegionalRepository.findByPeritoId(peritoId)
                .orElseGet(() -> {
                    GerenteRegional novo = new GerenteRegional();
                    novo.setPerito(perito);
                    novo.setRegional(regional);
                    novo.setUuid(UUID.randomUUID().toString());
                    return gerenteRegionalRepository.save(novo);
                });

        // Adiciona permissões de gerente se ainda não tiver
        Usuario usuario = perito.getUsuario();
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

        // Remove permissões de gerente, mantém só as de perito
        for (Long idPermissao : PERMISSOES_GERENTE) {
            UsuarioPermissaoId upId = new UsuarioPermissaoId(usuario.getId(), idPermissao);
            if (usuarioPermissaoRepository.existsById(upId)) {
                usuarioPermissaoRepository.deleteById(upId);
            }
        }

        // Garante permissões de perito (opcional, se quiser garantir)
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
