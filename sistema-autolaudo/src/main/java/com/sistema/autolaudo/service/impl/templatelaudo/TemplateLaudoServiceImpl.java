package com.sistema.autolaudo.service.impl.templatelaudo;

import com.sistema.autolaudo.dto.templatelaudo.TemplateLaudoRequest;
import com.sistema.autolaudo.dto.templatelaudo.TemplateLaudoResponse;
import com.sistema.autolaudo.mapper.templatelaudo.TemplateLaudoMapper;
import com.sistema.autolaudo.model.templatelaudo.TemplateLaudo;
import com.sistema.autolaudo.repository.templatelaudo.TemplateLaudoRepository;
import com.sistema.autolaudo.service.templatelaudo.TemplateLaudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateLaudoServiceImpl implements TemplateLaudoService {

    private final TemplateLaudoRepository templateLaudoRepository;
    private final TemplateLaudoMapper mapper;

    @Override
    public TemplateLaudoResponse criarTemplate(TemplateLaudoRequest request) {
        TemplateLaudo template = mapper.toEntity(request);
        TemplateLaudo saved = templateLaudoRepository.save(template);
        return mapper.toResponse(saved);
    }

    @Override
    public TemplateLaudoResponse buscarPorId(Long idTemplate) {
        return templateLaudoRepository.findById(idTemplate)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Template não encontrado"));
    }

    @Override
    public List<TemplateLaudoResponse> listarTodos() {
        return templateLaudoRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TemplateLaudoResponse atualizarTemplate(Long idTemplate, TemplateLaudoRequest request) {
        TemplateLaudo template = templateLaudoRepository.findById(idTemplate)
                .orElseThrow(() -> new RuntimeException("Template não encontrado"));
        template.setNome(request.getNome());
        template.setVersao(request.getVersao());
        TemplateLaudo updated = templateLaudoRepository.save(template);
        return mapper.toResponse(updated);
    }

    @Override
    public void deletarTemplate(Long idTemplate) {
        if (!templateLaudoRepository.existsById(idTemplate)) {
            throw new RuntimeException("Template não encontrado");
        }
        templateLaudoRepository.deleteById(idTemplate);
    }
}
