package coop.bancocredicoop.omnited.rabbit.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.model.SessionDatos;
import coop.bancocredicoop.omnited.rabbit.RabbitMessageHandler;
import coop.bancocredicoop.omnited.service.RedisService;
import java.util.Set;

public class UsuarioSessionHandler implements RabbitMessageHandler {

    private final RedisService redisService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UsuarioSessionHandler(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void handle(String idMensaje, String type, String jsonPayload, Integer idSector) throws Exception {

        String wbSessionID = redisService.temporalGetWebSocketSessionByMessageID(idMensaje);

        SessionDatos sessionDatos = objectMapper.readValue(jsonPayload, SessionDatos.class);

        Integer idUsuario = sessionDatos.getIdUsuario();
        Integer idPerfil = sessionDatos.getIdPerfil();
        Set<Integer> idSectores = sessionDatos.getIdSectores();
        
        redisService.addSessionToSectors(wbSessionID, idPerfil, idSectores);
        redisService.usuariosMapUsuarioIDToWebSocketSession(idUsuario, wbSessionID);
    }
}