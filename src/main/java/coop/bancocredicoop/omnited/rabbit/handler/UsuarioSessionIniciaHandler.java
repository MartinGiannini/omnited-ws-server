package coop.bancocredicoop.omnited.rabbit.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.exposition.SessionDTO;
import coop.bancocredicoop.omnited.rabbit.RabbitMessageHandler;
import coop.bancocredicoop.omnited.service.RedisService;
import java.util.Set;

public class UsuarioSessionIniciaHandler implements RabbitMessageHandler {

    private final RedisService redisService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UsuarioSessionIniciaHandler(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void handle(String idMensaje, String type, String jsonPayload, Integer idSector) throws Exception {

        String wbSessionID = redisService.temporalGetWebSocketSessionByMessageID(idMensaje);

        System.out.println("El jsonPayload que estoy recibiendo es: " + jsonPayload);
        
        
        SessionDTO usuarioDatos = objectMapper.readValue(jsonPayload, SessionDTO.class);

        Integer idUsuario = usuarioDatos.getUsuario().getIdUsuario();
        String usuarioUsuario = usuarioDatos.getUsuario().getUsuarioUsuario();
        Integer idPerfil = usuarioDatos.getUsuario().getUsuarioPerfil().getIdPerfil();
        Set<Integer> idSectores = usuarioDatos.getIdSectores();

        /**
         * Establezco los vinculos entre el websessionID y los sectores a los
         * que pertenece. Esto depende del idPerfil ya que un perfil de
         * operador, solo pertenecerá a 1 sector.
         */
        redisService.addSessionAndSectors(wbSessionID, idPerfil, idSectores);

        /**
         * Se guarda de manera temporal el vinculo entre el IdUsuario y el
         * websessionID. Sirve para saber  quien devolver el mensaje actual,
         * teniendo en cuenta que tiene 1 minuto para responder la petición.
         */
        redisService.usuariosMapUsuarioIDToWebSocketSession(idUsuario, wbSessionID);
        
        /**
         * Establezco el vínculo entre el websessionID y el nombre de usuario.
         */
        redisService.usuariosMapWebSocketSessionToUsuarioUsuario(wbSessionID, usuarioUsuario);
        
        /**
         * Establezco el vínculo entre el websessionID y el idUsuario. 
         */
        redisService.usuariosMapWebSocketSessionToIdUsuario(wbSessionID, idUsuario);
    }
}
