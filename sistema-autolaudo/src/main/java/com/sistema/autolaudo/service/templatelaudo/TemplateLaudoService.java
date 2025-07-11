package com.sistema.autolaudo.service.templatelaudo;


import com.sistema.autolaudo.dto.templatelaudo.TemplateLaudoRequest;
import com.sistema.autolaudo.dto.templatelaudo.TemplateLaudoResponse;

import java.util.List;

public interface TemplateLaudoService {
    TemplateLaudoResponse criarTemplate(TemplateLaudoRequest request);
    TemplateLaudoResponse buscarPorId(Long idTemplate);
    List<TemplateLaudoResponse> listarTodos();
    TemplateLaudoResponse atualizarTemplate(Long idTemplate, TemplateLaudoRequest request);
    void deletarTemplate(Long idTemplate);
}
