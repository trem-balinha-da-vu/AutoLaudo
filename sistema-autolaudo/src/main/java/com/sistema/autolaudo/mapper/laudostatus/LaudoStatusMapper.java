package com.sistema.autolaudo.mapper.laudostatus;
import com.sistema.autolaudo.dto.laudostatus.LaudoStatusResponse;
import com.sistema.autolaudo.model.laudostatus.LaudoStatus;
import org.springframework.stereotype.Component;

@Component
public class LaudoStatusMapper {

    public LaudoStatusResponse toResponse(LaudoStatus status) {
        if (status == null) return null;
        LaudoStatusResponse resp = new LaudoStatusResponse();
        resp.setIdStatus(status.getIdStatus());
        resp.setNome(status.getNome());
        return resp;
    }
}
