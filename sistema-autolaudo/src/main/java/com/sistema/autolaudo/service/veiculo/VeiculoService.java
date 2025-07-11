package com.sistema.autolaudo.service.veiculo;

import com.sistema.autolaudo.dto.veiculo.VeiculoRequest;
import com.sistema.autolaudo.dto.veiculo.VeiculoResponse;

import java.util.List;

public interface VeiculoService {
    VeiculoResponse criarVeiculo(VeiculoRequest request);
    VeiculoResponse buscarPorPlaca(String placa);
    List<VeiculoResponse> listarTodos();
    VeiculoResponse atualizarVeiculo(String placa, VeiculoRequest request);
    void deletarVeiculo(String placa);
}
