package coop.bancocredicoop.omnited.rabbit.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.exposition.SessionDTO;
import coop.bancocredicoop.omnited.rabbit.RabbitMessageHandler;
import coop.bancocredicoop.omnited.service.RedisService;
import java.util.Set;

public class UsuarioSessionFinalizaHandler implements RabbitMessageHandler {

    private final RedisService redisService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UsuarioSessionFinalizaHandler(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void handle(String idMensaje, String type, String jsonPayload, Integer idSector) throws Exception {

        String wbSessionID = redisService.temporalGetWebSocketSessionByMessageID(idMensaje);

        System.out.println("El jsonPayload que estoy recibiendo es: " + jsonPayload);
        
        
        SessionDTO usuarioDatos = objectMapper.readValue(jsonPayload, SessionDTO.class);

        Integer idUsuario = usuarioDatos.getUsuario().getIdUsuario();
        Set<Integer> idSectores = usuarioDatos.getIdSectores();

        /**
         * Elimino los sectores del websocketId
         */
        redisService.removeSectoresFromSession(wbSessionID, idSectores);

        /**
         * Elimino los websocketID de los sectores.
         */
        redisService.removeSessionFromSectors(wbSessionID, idSectores);
        
        /**
         * Se terminan de eliminar los registros
         */
        redisService.usuarioRemoveWebSocketSession(wbSessionID);
        redisService.usuariosRemoveIdUsuario(idUsuario);
    }
}
