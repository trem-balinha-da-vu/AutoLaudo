package com.sistema.autolaudo.service.formulariolaudo;


import com.sistema.autolaudo.dto.formulariolaudo.FormularioLaudoRequest;
import com.sistema.autolaudo.dto.formulariolaudo.FormularioLaudoResponse;
import com.sistema.autolaudo.model.formulariolaudo.FormularioLaudo;

import java.util.List;

public interface FormularioLaudoService {
    FormularioLaudo criarFormulario(FormularioLaudoRequest req);
    FormularioLaudo preencherCampo(Long idFormulario, String nomeCampo, String valor);
    FormularioLaudo buscarPorId(Long idFormulario);
    FormularioLaudo buscarUltimoPorPerito(Long idPerito);
    List<FormularioLaudo> listarPorPerito(Long idPerito);
}
