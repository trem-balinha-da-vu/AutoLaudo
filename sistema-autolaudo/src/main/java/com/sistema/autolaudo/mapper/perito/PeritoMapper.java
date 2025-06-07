package com.sistema.autolaudo.mapper.perito;

import com.sistema.autolaudo.dto.perito.PeritoResponse;
import com.sistema.autolaudo.dto.usuario.UsuarioResponse;
import com.sistema.autolaudo.mapper.usuario.UsuarioMapper;
import com.sistema.autolaudo.model.perito.Perito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeritoMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    public PeritoResponse toResponse(Perito perito) {
        PeritoResponse response = new PeritoResponse();
        response.setId(perito.getId());
        response.setUuid(perito.getUuid());
        response.setCrmPerito(perito.getCrmPerito());
        response.setAreaEspecialidade(perito.getAreaEspecialidade());
        response.setNivelExperiencia(perito.getNivelExperiencia());
        response.setTelefoneContato(perito.getTelefoneContato());
        response.setAtivo(perito.getAtivo());
        // Mapeia o usuário para UsuarioResponse e aninha
        UsuarioResponse usuarioResponse = usuarioMapper.toResponse(perito.getUsuario());
        response.setUsuario(usuarioResponse);
        return response;
    }
}
