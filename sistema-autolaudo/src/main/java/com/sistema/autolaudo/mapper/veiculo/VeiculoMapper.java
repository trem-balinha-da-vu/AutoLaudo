package com.sistema.autolaudo.mapper.veiculo;

import com.sistema.autolaudo.dto.veiculo.VeiculoRequest;
import com.sistema.autolaudo.dto.veiculo.VeiculoResponse;
import com.sistema.autolaudo.model.veiculo.Veiculo;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    public Veiculo toEntity(VeiculoRequest request) {
        if (request == null) return null;
        return Veiculo.builder()
                .placa(request.getPlaca())
                .chassi(request.getChassi())
                .renavam(request.getRenavam())
                .modelo(request.getModelo())
                .anoFabricacao(request.getAnoFabricacao())
                .statusRegistro(request.getStatusRegistro())
                .build();
    }

    public VeiculoResponse toResponse(Veiculo veiculo) {
        if (veiculo == null) return null;
        VeiculoResponse response = new VeiculoResponse();
        response.setPlaca(veiculo.getPlaca());
        response.setChassi(veiculo.getChassi());
        response.setRenavam(veiculo.getRenavam());
        response.setModelo(veiculo.getModelo());
        response.setAnoFabricacao(veiculo.getAnoFabricacao());
        response.setStatusRegistro(veiculo.getStatusRegistro());
        return response;
    }
}
