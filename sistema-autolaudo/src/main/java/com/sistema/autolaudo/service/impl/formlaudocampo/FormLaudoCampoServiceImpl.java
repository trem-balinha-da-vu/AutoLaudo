package com.sistema.autolaudo.service.impl.formlaudocampo;

import com.sistema.autolaudo.dto.formlaudocampo.FormLaudoCampoRequest;
import com.sistema.autolaudo.dto.formlaudocampo.FormLaudoCampoResponse;
import com.sistema.autolaudo.mapper.formlaudocampo.FormLaudoCampoMapper;
import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampo;
import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampoId;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import com.sistema.autolaudo.repository.formlaudocampo.FormLaudoCampoRepository;
import com.sistema.autolaudo.repository.formulariolaudo.FormularioLaudoRepository;
import com.sistema.autolaudo.service.formlaudocampo.FormLaudoCampoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormLaudoCampoServiceImpl implements FormLaudoCampoService {

    private final FormLaudoCampoRepository formLaudoCampoRepository;
    private final FormularioLaudoRepository formularioLaudoRepository;
    private final FormLaudoCampoMapper mapper;

    @Override
    public FormLaudoCampoResponse criarCampo(FormLaudoCampoRequest request) {
        FormularioLaudo formulario = formularioLaudoRepository.findById(request.getIdFormulario())
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado"));
        FormLaudoCampo campo = mapper.toEntity(request, formulario);
        FormLaudoCampo saved = formLaudoCampoRepository.save(campo);
        return mapper.toResponse(saved);
    }

    @Override
    public FormLaudoCampoResponse buscarPorId(Long idFormulario, String nomeCampo) {
        FormLaudoCampoId id = new FormLaudoCampoId(idFormulario, nomeCampo);
        return formLaudoCampoRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Campo não encontrado"));
    }

    @Override
    public List<FormLaudoCampoResponse> listarPorFormulario(Long idFormulario) {
        return formLaudoCampoRepository.findByFormularioLaudo_IdFormulario(idFormulario).stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FormLaudoCampoResponse atualizarCampo(FormLaudoCampoRequest request) {
        FormLaudoCampoId id = new FormLaudoCampoId(request.getIdFormulario(), request.getNomeCampo());
        FormLaudoCampo campo = formLaudoCampoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campo não encontrado"));
        campo.setValor(request.getValor());
        FormLaudoCampo updated = formLaudoCampoRepository.save(campo);
        return mapper.toResponse(updated);
    }

    @Override
    public void deletarCampo(Long idFormulario, String nomeCampo) {
        FormLaudoCampoId id = new FormLaudoCampoId(idFormulario, nomeCampo);
        if (!formLaudoCampoRepository.existsById(id)) {
            throw new RuntimeException("Campo não encontrado");
        }
        formLaudoCampoRepository.deleteById(id);
    }
}

