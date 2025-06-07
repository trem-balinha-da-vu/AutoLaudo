package com.sistema.autolaudo.controller.permissao;

import com.sistema.autolaudo.dto.permissao.UsuarioPermissaoResponse;
import com.sistema.autolaudo.dto.common.BaseReturn;
import com.sistema.autolaudo.service.permissao.UsuarioPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios-permissoes")
public class UsuarioPermissaoController {

    @Autowired
    private UsuarioPermissaoService usuarioPermissaoService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public BaseReturn<UsuarioPermissaoResponse> create(@RequestParam("usuarioId") Long usuarioId,
                                                       @RequestParam("permissaoId") Long permissaoId) {
        UsuarioPermissaoResponse response = usuarioPermissaoService.criar(usuarioId, permissaoId);
        return new BaseReturn<>(response);
    }

    @DeleteMapping(path = "/{usuarioId}/{permissaoId}")
    public void delete(@PathVariable("usuarioId") Long usuarioId, @PathVariable("permissaoId") Long permissaoId) {
        usuarioPermissaoService.deletar(usuarioId, permissaoId);
    }

    @GetMapping(path = "/usuario/{usuarioId}", produces = "application/json")
    public BaseReturn<List<UsuarioPermissaoResponse>> getListByUsuario(@PathVariable("usuarioId") Long usuarioId) {
        List<UsuarioPermissaoResponse> response = usuarioPermissaoService.listarPorUsuario(usuarioId);
        return new BaseReturn<>(response);
    }
}
