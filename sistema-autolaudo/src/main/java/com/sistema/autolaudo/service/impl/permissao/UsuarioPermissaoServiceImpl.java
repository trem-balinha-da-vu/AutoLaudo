package com.sistema.autolaudo.service.impl.permissao;

import com.sistema.autolaudo.dto.permissao.UsuarioPermissaoResponse;
import com.sistema.autolaudo.mapper.permissao.UsuarioPermissaoMapper;
import com.sistema.autolaudo.model.permissao.Permissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissao;
import com.sistema.autolaudo.model.permissao.UsuarioPermissaoId;
import com.sistema.autolaudo.model.usuario.Usuario;
import com.sistema.autolaudo.repository.permissao.PermissaoRepository;
import com.sistema.autolaudo.repository.permissao.UsuarioPermissaoRepository;
import com.sistema.autolaudo.repository.usuario.UsuarioRepository;
import com.sistema.autolaudo.service.permissao.UsuarioPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioPermissaoServiceImpl implements UsuarioPermissaoService {

    @Autowired
    private UsuarioPermissaoRepository usuarioPermissaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private UsuarioPermissaoMapper usuarioPermissaoMapper;

    @Override
    @Transactional
    public UsuarioPermissaoResponse criar(Long usuarioId, Long permissaoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Permissao permissao = permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new RuntimeException("Permissão não encontrada"));
        UsuarioPermissao usuarioPermissao = usuarioPermissaoMapper.toModel(usuario, permissao);

        // Se usar id composto, setar:
        // usuarioPermissao.setId(new UsuarioPermissaoId(usuarioId, permissaoId));

        usuarioPermissao = usuarioPermissaoRepository.save(usuarioPermissao);
        return usuarioPermissaoMapper.toResponse(usuarioPermissao);
    }

    @Override
    @Transactional
    public void deletar(Long usuarioId, Long permissaoId) {
        UsuarioPermissaoId id = new UsuarioPermissaoId(usuarioId, permissaoId);
        usuarioPermissaoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioPermissaoResponse> listarPorUsuario(Long usuarioId) {
        List<UsuarioPermissao> list = usuarioPermissaoRepository.findByUsuarioId(usuarioId);
        return list.stream().map(usuarioPermissaoMapper::toResponse).collect(Collectors.toList());
    }
}
