package com.sistema.autolaudo.service.impl.selodigital;

import com.sistema.autolaudo.dto.selodigital.SeloDigitalRequest;
import com.sistema.autolaudo.dto.selodigital.SeloDigitalResponse;
import com.sistema.autolaudo.mapper.selodigital.SeloDigitalMapper;
import com.sistema.autolaudo.model.selodigital.SeloDigital;
import com.sistema.autolaudo.repository.selodigital.SeloDigitalRepository;
import com.sistema.autolaudo.service.selodigital.SeloDigitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeloDigitalServiceImpl implements SeloDigitalService {

    private final SeloDigitalRepository seloDigitalRepository;
    private final SeloDigitalMapper mapper;

    @Override
    public SeloDigitalResponse criarSelo(SeloDigitalRequest request) {
        SeloDigital selo = mapper.toEntity(request);
        SeloDigital saved = seloDigitalRepository.save(selo);
        return mapper.toResponse(saved);
    }

    @Override
    public SeloDigitalResponse buscarPorId(Long idSelo) {
        return seloDigitalRepository.findById(idSelo)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Selo não encontrado"));
    }

    @Override
    public List<SeloDigitalResponse> listarTodos() {
        return seloDigitalRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SeloDigitalResponse atualizarSelo(Long idSelo, SeloDigitalRequest request) {
        SeloDigital selo = seloDigitalRepository.findById(idSelo)
                .orElseThrow(() -> new RuntimeException("Selo não encontrado"));
        selo.setDataEmissao(request.getDataEmissao());
        selo.setAutoridadeEmissora(request.getAutoridadeEmissora());
        selo.setHashConformidade(request.getHashConformidade());
        SeloDigital updated = seloDigitalRepository.save(selo);
        return mapper.toResponse(updated);
    }

    @Override
    public void deletarSelo(Long idSelo) {
        if (!seloDigitalRepository.existsById(idSelo)) {
            throw new RuntimeException("Selo não encontrado");
        }
        seloDigitalRepository.deleteById(idSelo);
    }
}
