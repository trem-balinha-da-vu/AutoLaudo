package com.sistema.autolaudo.service.impl.norma;

import com.sistema.autolaudo.dto.norma.NormaRequest;
import com.sistema.autolaudo.dto.norma.NormaResponse;
import com.sistema.autolaudo.mapper.norma.NormaMapper;
import com.sistema.autolaudo.model.norma.Norma;
import com.sistema.autolaudo.repository.norma.NormaRepository;
import com.sistema.autolaudo.service.norma.NormaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NormaServiceImpl implements NormaService {

    private final NormaRepository normaRepository;
    private final NormaMapper normaMapper;

    @Override
    public NormaResponse criarNorma(NormaRequest request) {
        Norma norma = normaMapper.toEntity(request);
        Norma saved = normaRepository.save(norma);
        return normaMapper.toResponse(saved);
    }

    @Override
    public NormaResponse buscarNormaPorId(Long id) {
        return normaRepository.findById(id)
                .map(normaMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Norma não encontrada"));
    }

    @Override
    public List<NormaResponse> listarNormas() {
        return normaRepository.findAll().stream()
                .map(normaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NormaResponse atualizarNorma(Long id, NormaRequest request) {
        Norma norma = normaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Norma não encontrada"));
        norma.setNomeNorma(request.getNomeNorma());
        norma.setTipo(request.getTipo());
        norma.setTextoNorma(request.getTextoNorma());
        Norma updated = normaRepository.save(norma);
        return normaMapper.toResponse(updated);
    }

    @Override
    public void deletarNorma(Long id) {
        if (!normaRepository.existsById(id)) {
            throw new RuntimeException("Norma não encontrada");
        }
        normaRepository.deleteById(id);
    }
}
