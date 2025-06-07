package com.sistema.autolaudo.service.perfil.impl;

import com.sistema.autolaudo.dto.perfil.PerfilRequest;
import com.sistema.autolaudo.dto.perfil.PerfilResponse;
import com.sistema.autolaudo.mapper.perfil.PerfilMapper;
import com.sistema.autolaudo.model.perfil.Perfil;
import com.sistema.autolaudo.repository.perfil.PerfilRepository;
import com.sistema.autolaudo.service.perfil.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PerfilMapper perfilMapper;

    @Override
    @Transactional
    public PerfilResponse criarPerfil(PerfilRequest request) {
        Perfil perfil = perfilMapper.toModel(request);
        perfil = perfilRepository.save(perfil);
        return perfilMapper.toResponse(perfil);
    }

    @Override
    @Transactional
    public PerfilResponse atualizarPerfil(Long id, PerfilRequest request) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        perfilMapper.updateModel(request, perfil);
        perfil = perfilRepository.save(perfil);
        return perfilMapper.toResponse(perfil);
    }

    @Override
    @Transactional
    public void deletarPerfil(Long id) {
        perfilRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PerfilResponse buscarPorId(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
        return perfilMapper.toResponse(perfil);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PerfilResponse> buscarTodos() {
        List<Perfil> perfis = perfilRepository.findAll();
        return perfis.stream().map(perfilMapper::toResponse).collect(Collectors.toList());
    }
}
