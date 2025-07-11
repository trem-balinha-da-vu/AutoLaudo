package com.sistema.autolaudo.service.impl.formulariolaudo;


import com.sistema.autolaudo.dto.formulariolaudo.FormularioLaudoRequest;
import com.sistema.autolaudo.dto.formulariolaudo.FormularioLaudoResponse;
import com.sistema.autolaudo.mapper.formulariolaudo.FormularioLaudoMapper;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import com.sistema.autolaudo.model.laudoveicular.LaudoVeicular;
import com.sistema.autolaudo.model.templatelaudo.TemplateLaudo;
import com.sistema.autolaudo.repository.formulariolaudo.FormularioLaudoRepository;
import com.sistema.autolaudo.repository.laudoveicular.LaudoVeicularRepository;
import com.sistema.autolaudo.repository.templatelaudo.TemplateLaudoRepository;
import com.sistema.autolaudo.repository.usuario.UsuarioRepository;
import com.sistema.autolaudo.service.formulariolaudo.FormularioLaudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormularioLaudoServiceImpl implements FormularioLaudoService {

    private final FormularioLaudoRepository formularioRepo;
    private final TemplateLaudoRepository templateRepo;
    private final UsuarioRepository usuarioRepo;
    private LaudoVeicularRepository laudoRepo;

    @Override
    public FormularioLaudo criarFormulario(FormularioLaudoRequest req) {
        TemplateLaudo template = templateRepo.findById(req.getIdTemplate())
                .orElseThrow(() -> new RuntimeException("Template não encontrado"));

        FormularioLaudo form = FormularioLaudo.builder()
                .template(template)
                .modoOffline(req.getModoOffline())
                .build();

        // Pode preencher campos iniciais se vierem no request
        if (req.getCampos() != null) {
            req.getCampos().forEach((nome, valor) -> form.preencherCampo(nome, valor));
        }

        return formularioRepo.save(form);
    }

    @Override
    public FormularioLaudo preencherCampo(Long idFormulario, String nomeCampo, String valor) {
        FormularioLaudo form = formularioRepo.findById(idFormulario)
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado"));
        form.preencherCampo(nomeCampo, valor);
        return formularioRepo.save(form);
    }

    @Override
    public FormularioLaudo buscarPorId(Long idFormulario) {
        return formularioRepo.findById(idFormulario)
                .orElseThrow(() -> new RuntimeException("Formulário não encontrado"));
    }

    @Override
    public FormularioLaudo buscarUltimoPorPerito(Long idPerito) {
        // Busca pelo laudo mais recente do perito que tenha formulário
        LaudoVeicular laudo = laudoRepo.findTopByPerito_IdOrderByDataCriacaoDesc(idPerito)
                .orElse(null);
        return laudo != null ? laudo.getFormularioLaudo() : null;
    }

    @Override
    public List<FormularioLaudo> listarPorPerito(Long idPerito) {
        // Busca todos laudos do perito com formulário associado
        return laudoRepo.findByPerito_Id(idPerito).stream()
                .map(LaudoVeicular::getFormularioLaudo)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
