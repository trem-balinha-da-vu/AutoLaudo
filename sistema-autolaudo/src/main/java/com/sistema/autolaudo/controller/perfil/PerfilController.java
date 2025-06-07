package com.sistema.autolaudo.controller.perfil;

import com.sistema.autolaudo.dto.perfil.PerfilRequest;
import com.sistema.autolaudo.dto.perfil.PerfilResponse;
import com.sistema.autolaudo.dto.common.BaseReturn;
import com.sistema.autolaudo.service.perfil.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping(produces = "application/json")
    public BaseReturn<List<PerfilResponse>> getList() {
        List<PerfilResponse> response = perfilService.buscarTodos();
        return new BaseReturn<>(response);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public BaseReturn<PerfilResponse> getById(@PathVariable("id") Long id) {
        PerfilResponse response = perfilService.buscarPorId(id);
        return new BaseReturn<>(response);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public BaseReturn<PerfilResponse> create(@Validated @RequestBody PerfilRequest request) {
        PerfilResponse response = perfilService.criarPerfil(request);
        return new BaseReturn<>(response);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public BaseReturn<PerfilResponse> update(@Validated @RequestBody PerfilRequest request, @PathVariable("id") Long id) {
        PerfilResponse response = perfilService.atualizarPerfil(id, request);
        return new BaseReturn<>(response);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        perfilService.deletarPerfil(id);
    }
}
