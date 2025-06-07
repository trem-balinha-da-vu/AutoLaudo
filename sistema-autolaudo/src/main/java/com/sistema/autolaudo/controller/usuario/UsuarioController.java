package com.sistema.autolaudo.controller.usuario;

import com.sistema.autolaudo.dto.permissao.PermissaoResponse;
import com.sistema.autolaudo.dto.usuario.UsuarioRequest;
import com.sistema.autolaudo.dto.usuario.UsuarioResponse;
import com.sistema.autolaudo.dto.common.BaseReturn;
import com.sistema.autolaudo.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/{id}/permissoes", produces = "application/json")
    public BaseReturn<List<PermissaoResponse>> getPermissoesByUsuario(@PathVariable("id") Long id) {
        List<PermissaoResponse> permissoes = usuarioService.listarPermissoesDoUsuario(id);
        return new BaseReturn<>(permissoes);
    }

    @GetMapping(produces = "application/json")
    public BaseReturn<List<UsuarioResponse>> getList() {
        List<UsuarioResponse> response = usuarioService.buscarTodos();
        return new BaseReturn<>(response);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public BaseReturn<UsuarioResponse> getById(@PathVariable("id") Long id) {
        UsuarioResponse response = usuarioService.buscarPorId(id);
        return new BaseReturn<>(response);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public BaseReturn<UsuarioResponse> create(@Validated @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.criarUsuario(request);
        return new BaseReturn<>(response);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public BaseReturn<UsuarioResponse> update(@Validated @RequestBody UsuarioRequest request, @PathVariable("id") Long id) {
        UsuarioResponse response = usuarioService.atualizarUsuario(id, request);
        return new BaseReturn<>(response);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id) {
        usuarioService.deletarUsuario(id);
    }
}
