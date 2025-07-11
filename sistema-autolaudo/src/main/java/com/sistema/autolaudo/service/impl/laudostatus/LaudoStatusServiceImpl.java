package com.sistema.autolaudo.service.impl.laudostatus;

import com.sistema.autolaudo.dto.laudostatus.LaudoStatusResponse;
import com.sistema.autolaudo.mapper.laudostatus.LaudoStatusMapper;
import com.sistema.autolaudo.repository.laudostatus.LaudoStatusRepository;
import com.sistema.autolaudo.service.laudostatus.LaudoStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaudoStatusServiceImpl implements LaudoStatusService {

    private final LaudoStatusRepository laudoStatusRepository;
    private final LaudoStatusMapper mapper;


    @Override
    public LaudoStatusResponse buscarPorId(Long idStatus) {
        return laudoStatusRepository.findById(idStatus)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }

    @Override
    public List<LaudoStatusResponse> listarTodos() {
        return laudoStatusRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

}
