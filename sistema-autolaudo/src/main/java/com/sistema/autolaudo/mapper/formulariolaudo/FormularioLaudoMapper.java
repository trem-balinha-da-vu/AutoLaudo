package com.sistema.autolaudo.mapper.formulariolaudo;


import com.sistema.autolaudo.dto.formulariolaudo.FormularioLaudoRequest;
import com.sistema.autolaudo.dto.formulariolaudo.FormularioLaudoResponse;
import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampo;
import com.sistema.autolaudo.model.formlaudocampo.FormLaudoCampoId;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;
import com.sistema.autolaudo.model.templatelaudo.TemplateLaudo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FormularioLaudoMapper {

    // Recebe a entidade TemplateLaudo já buscada no service!
    public FormularioLaudo toEntity(FormularioLaudoRequest request, TemplateLaudo templateLaudo) {
        if (request == null) return null;

        FormularioLaudo form = FormularioLaudo.builder()
                .modoOffline(request.getModoOffline())
                .template(templateLaudo)
                .build();

        // Monta a lista de campos a partir do Map do request
        if (request.getCampos() != null && !request.getCampos().isEmpty()) {
            List<FormLaudoCampo> camposList = request.getCampos().entrySet().stream()
                    .map(entry -> FormLaudoCampo.builder()
                            .id(new FormLaudoCampoId(null, entry.getKey())) // id_formulario será setado depois pelo JPA
                            .valor(entry.getValue())
                            .formularioLaudo(form)
                            .build()
                    )
                    .collect(Collectors.toList());
            form.setCampos(camposList);
        }

        return form;
    }

    public FormularioLaudoResponse toResponse(FormularioLaudo entity) {
        if (entity == null) return null;
        FormularioLaudoResponse resp = new FormularioLaudoResponse();
        resp.setIdFormulario(entity.getIdFormulario());
        resp.setModoOffline(entity.getModoOffline());
        resp.setIdTemplate(entity.getTemplate() != null ? entity.getTemplate().getIdTemplate() : null);
        resp.setNomeTemplate(entity.getTemplate() != null ? entity.getTemplate().getNome() : null);

        // Transforma a lista de campos em Map<String, String>
        if (entity.getCampos() != null && !entity.getCampos().isEmpty()) {
            Map<String, String> camposMap = entity.getCampos().stream()
                    .collect(Collectors.toMap(
                            campo -> campo.getId().getNomeCampo(),
                            FormLaudoCampo::getValor
                    ));
            resp.setCampos(camposMap);
        }

        return resp;
    }
}
