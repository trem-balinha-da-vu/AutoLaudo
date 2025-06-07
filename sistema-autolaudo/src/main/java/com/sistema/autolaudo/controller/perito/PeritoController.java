package com.sistema.autolaudo.controller.perito;

import com.sistema.autolaudo.dto.common.BaseReturn;
import com.sistema.autolaudo.dto.gerenteregional.GerenteRegionalResponse;
import com.sistema.autolaudo.dto.gerenteregional.PromoverGerenteRequest;
import com.sistema.autolaudo.dto.perito.PeritoResponse;
import com.sistema.autolaudo.service.perito.PeritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peritos")
public class PeritoController {

    @Autowired
    private PeritoService peritoService;

    // Promover perito para gerente regional
    @PostMapping("/{peritoId}/promover-gerente")
    public BaseReturn<GerenteRegionalResponse> promoverParaGerente(@PathVariable Long peritoId, @RequestBody PromoverGerenteRequest request) {
        GerenteRegionalResponse response  = peritoService.promoverParaGerente(peritoId, request.getRegional());
        return new BaseReturn<> (response);
    }

    // Rebaixar gerente regional para perito
    @PostMapping("/{peritoId}/rebaixar-perito")
    public BaseReturn<PeritoResponse> rebaixarParaPerito(@PathVariable Long peritoId) {
        PeritoResponse response = peritoService.rebaixarParaPerito(peritoId);
        return new BaseReturn<>(response);
    }

    @GetMapping("/{id}")
    public PeritoResponse getById(@PathVariable Long id) {
        return peritoService.getById(id);
    }

    @GetMapping
    public List<PeritoResponse> getAll() {
        return peritoService.getAll();
    }
}
