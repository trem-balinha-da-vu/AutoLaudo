package com.sistema.autolaudo.service.impl.integracao_detran;

import com.sistema.autolaudo.dto.integracao_detran.IntegracaoDetranResponse;
import com.sistema.autolaudo.mapper.integracao_detran.IntegracaoDetranMapper;
import com.sistema.autolaudo.model.integracao_detran.IntegracaoDetran;
import com.sistema.autolaudo.repository.integracao_detran.IntegracaoDetranRepository;
import com.sistema.autolaudo.service.integracao_detran.IntegracaoDetranService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class IntegracaoDetranServiceImpl implements IntegracaoDetranService {

    private final IntegracaoDetranRepository integracaoRepository;
    private final IntegracaoDetranMapper mapper;

    @Override
    public IntegracaoDetranResponse consultarVeiculoPorChassi(String chassi) {
        // Aqui integraria com a API do DETRAN real. Simulando abaixo:
        // Simulação de resposta da API DETRAN
        Map<String, Object> respostaMock = new HashMap<>();
        respostaMock.put("chassi", chassi);
        respostaMock.put("placa", "ABC1D23");
        respostaMock.put("modelo", "FIAT UNO");
        respostaMock.put("ano_fabricacao", 2018);
        respostaMock.put("status_registro", "REGULAR");
        respostaMock.put("consulta_via", "MOCK");

        // Converte para JSON
        String resultadoJson = new com.fasterxml.jackson.databind.ObjectMapper()
                .valueToTree(respostaMock).toString();

        // Salva o log na tabela
        IntegracaoDetran log = IntegracaoDetran.builder()
                .api("busca_veiculo_por_chassi")
                .dataChamada(LocalDateTime.now())
                .resultado(resultadoJson)
                .placaVeiculo((String) respostaMock.get("placa"))
                .build();

        IntegracaoDetran saved = integracaoRepository.save(log);
        return mapper.toResponse(saved);
    }
}
