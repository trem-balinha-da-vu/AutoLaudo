package com.sistema.autolaudo.service.impl.permissao;

import com.sistema.autolaudo.dto.permissao.PermissaoRequest;
import com.sistema.autolaudo.dto.permissao.PermissaoResponse;
import com.sistema.autolaudo.mapper.permissao.PermissaoMapper;
import com.sistema.autolaudo.model.permissao.Permissao;
import com.sistema.autolaudo.repository.permissao.PermissaoRepository;
import com.sistema.autolaudo.service.permissao.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissaoServiceImpl implements PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PermissaoMapper permissaoMapper;

    @Override
    @Transactional
    public PermissaoResponse criarPermissao(PermissaoRequest request) {
        Permissao permissao = permissaoMapper.toModel(request);
        permissao = permissaoRepository.save(permissao);
        return permissaoMapper.toResponse(permissao);
    }

    @Override
    @Transactional
    public PermissaoResponse atualizarPermissao(Long id, PermissaoRequest request) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permissão não encontrada"));
        permissaoMapper.updateModel(request, permissao);
        permissao = permissaoRepository.save(permissao);
        return permissaoMapper.toResponse(permissao);
    }

    @Override
    @Transactional
    public void deletarPermissao(Long id) {
        permissaoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissaoResponse buscarPorId(Long id) {
        Permissao permissao = permissaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permissão não encontrada"));
        return permissaoMapper.toResponse(permissao);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissaoResponse> buscarTodos() {
        List<Permissao> permissoes = permissaoRepository.findAll();
        return permissoes.stream().map(permissaoMapper::toResponse).collect(Collectors.toList());
    }
}
