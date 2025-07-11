package com.sistema.autolaudo.service.formlaudocampo;


import com.sistema.autolaudo.dto.formlaudocampo.FormLaudoCampoRequest;
import com.sistema.autolaudo.dto.formlaudocampo.FormLaudoCampoResponse;

import java.util.List;

public interface FormLaudoCampoService {
    FormLaudoCampoResponse criarCampo(FormLaudoCampoRequest request);
    FormLaudoCampoResponse buscarPorId(Long idFormulario, String nomeCampo);
    List<FormLaudoCampoResponse> listarPorFormulario(Long idFormulario);
    FormLaudoCampoResponse atualizarCampo(FormLaudoCampoRequest request);
    void deletarCampo(Long idFormulario, String nomeCampo);
}
