package coop.bancocredicoop.omnited.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.exposition.SessionDTO;
import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import coop.bancocredicoop.omnited.service.RedisService;
import coop.bancocredicoop.omnited.service.WebSocketService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.web.socket.PongMessage;

public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = Logger.getLogger(WebSocketHandler.class.getName());
    private final WebSocketToRabbit webSocketToRabbit;
    private final WebSocketService webSocketService;
    private final RedisService redisService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    UUID uuid = UUID.randomUUID();

    // Constructor con inyección de dependencia
    public WebSocketHandler(
            WebSocketToRabbit webSocketToRabbit,
            WebSocketService webSocketService,
            RedisService redisService
    ) {
        this.redisService = redisService;
        this.webSocketToRabbit = webSocketToRabbit;
        this.webSocketService = webSocketService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.log(Level.INFO, "New connection established: {0}", session.getId());

        // Guardo en caché el hash: WEBSOCKETID - SESSION WEBSOCKET
        webSocketService.addSession(session.getId(), session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        try {
            JsonNode jsonNode = objectMapper.readTree(message.getPayload());

            String type = jsonNode.get("type").asText();

            switch (type) {
                case "sedebeborrar":
                    //session.sendMessage(new TextMessage("ping;" + pong));
                    break;
                default:
                    // Guardo el hash temporal en Redis: MENSAJEID - WEBSOCKETID
                    redisService.temporalMapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());
                    webSocketToRabbit.processMessage(message.getPayload());
                    LOGGER.log(Level.INFO, "Desde la sesion: ", session.getId());
                    LOGGER.log(Level.INFO, "Llegó el siguiente Mensaje desde el cliente: ", new Object[]{session.getId(), message.getPayload()});
                    break;
            }
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Received message from {0}: {1}", e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, org.springframework.web.socket.CloseStatus status) throws Exception {
        String usuarioUsuario = "";
        Integer idUsuario = 0;
        System.out.println("Se cierra la conexion: " + webSocketSession.getId());
        System.out.println("Motivos de cierre: "+status.getReason());
        System.out.println("Obtengo el Usuario dado el mapeo que hice con el session");

        try {
            usuarioUsuario = redisService.usuariosGetUsuarioUsuarioByWebSocketSession(webSocketSession.getId());
            idUsuario = redisService.usuariosGetIdUsuarioByWebSocketSession(webSocketSession.getId());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "No se obtuvieron datos del usuario en el cierre de la sesion", e.getMessage());
        }

        System.out.println("El usuario es: " + usuarioUsuario + " y el idUsuario es: " + idUsuario);

        /**
         * Tengo que enviar los codigos de cierre y borrar todo a la vuelta de
         * la DB
         */
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setUsuarioUsuario(usuarioUsuario);
        usuario.setIdUsuario(idUsuario);
        Set<Integer> idSectores = redisService.getSectoresBySession(webSocketSession.getId()).stream()
                .map(obj -> (Integer) obj)
                .collect(Collectors.toSet());

        SessionDTO session = new SessionDTO(idSectores, usuario);
        String sessionJson = objectMapper.writeValueAsString(session);

        /**
         * Proceso los envios.
         */
        webSocketToRabbit.processMessageSelfOriginated(uuid.toString(), "usuariologoutWS", sessionJson);

        /*
        Set<Object> sectoresIds = redisService.getSectoresBySession(webSocketSession.getId());        
        
        redisService.removeSessionFromSectors(webSocketSession.getId(), sectoresIds);
        redisService.removeSectoresFromSession(webSocketSession.getId());

        webSocketService.removeSession(webSocketSession.getId());
         */
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.log(Level.SEVERE, "Error in session {0}: {1}", new Object[]{session.getId(), exception.getMessage()});
        session.close();
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        LOGGER.log(Level.INFO, "Recibido pong de la sesión: {0}", session.getId());
        // Aquí puedes agregar lógica adicional, por ejemplo, actualizar el estado de conexión o medir latencia.
    }
}
