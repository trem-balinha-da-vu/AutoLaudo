package com.sistema.autolaudo.service.impl.gerenteregional;

import com.sistema.autolaudo.dto.gerenteregional.GerenteRegionalResponse;
import com.sistema.autolaudo.mapper.gerenteregional.GerenteRegionalMapper;
import com.sistema.autolaudo.model.gerenteregional.GerenteRegional;
import com.sistema.autolaudo.repository.gerenteregional.GerenteRegionalRepository;
import com.sistema.autolaudo.service.gerenteregional.GerenteRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GerenteRegionalServiceImpl implements GerenteRegionalService {

    @Autowired
    GerenteRegionalRepository gerenteRegionalRepository;

    @Autowired
    GerenteRegionalMapper gerenteRegionalMapper;

    @Override
    @Transactional(readOnly = true)
    public GerenteRegionalResponse getById(Long id) {
        GerenteRegional gerente = gerenteRegionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gerente regional não encontrado"));
        return gerenteRegionalMapper.toResponse(gerente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GerenteRegionalResponse> getAll() {
        return gerenteRegionalRepository.findAll()
                .stream()
                .map(gerenteRegionalMapper::toResponse)
                .toList();
    }
}
