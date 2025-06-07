package com.sistema.autolaudo.controller.permissao;

import com.sistema.autolaudo.dto.permissao.PermissaoRequest;
import com.sistema.autolaudo.dto.permissao.PermissaoResponse;
import com.sistema.autolaudo.dto.common.BaseReturn;
import com.sistema.autolaudo.service.permissao.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping(produces = "application/json")
    public BaseReturn<List<PermissaoResponse>> getList() {
        List<PermissaoResponse> response = permissaoService.buscarTodos();
        return new BaseReturn<>(response);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public BaseReturn<PermissaoResponse> getById(@PathVariable("id") Long id) {
        PermissaoResponse response = permissaoService.buscarPorId(id);
        return new BaseReturn<>(response);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public BaseReturn<PermissaoResponse> create(@Validated @RequestBody PermissaoRequest request) {
        PermissaoResponse response = permissaoService.criarPermissao(request);
        return new BaseReturn<>(response);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public BaseReturn<PermissaoResponse> update(@Validated @RequestBody PermissaoRequest request, @PathVariable("id") Long id) {
        PermissaoResponse response = permissaoService.atualizarPermissao(id, request);
        return new BaseReturn<>(response);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        permissaoService.deletarPermissao(id);
    }
}
