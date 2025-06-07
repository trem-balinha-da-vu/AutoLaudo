package com.sistema.autolaudo.controller.gerenteregional;

import com.sistema.autolaudo.dto.gerenteregional.GerenteRegionalResponse;
import com.sistema.autolaudo.service.gerenteregional.GerenteRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gerentes")
public class GerenteRegionalController {

    @Autowired
    private GerenteRegionalService gerenteRegionalService;

    @GetMapping("/{id}")
    public GerenteRegionalResponse getById(@PathVariable Long id) {
        return gerenteRegionalService.getById(id);
    }

    @GetMapping
    public List<GerenteRegionalResponse> getAll() {
        return gerenteRegionalService.getAll();
    }
}
