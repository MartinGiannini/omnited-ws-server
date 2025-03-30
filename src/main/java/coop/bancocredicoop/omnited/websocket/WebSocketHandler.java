package coop.bancocredicoop.omnited.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.service.RedisService;
import coop.bancocredicoop.omnited.service.WebSocketService;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.socket.PongMessage;

public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = Logger.getLogger(WebSocketHandler.class.getName());
    private final WebSocketToRabbit webSocketToRabbit;
    private final WebSocketService webSocketService;
    private final RedisService redisService;

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
            ObjectMapper objectMapper = new ObjectMapper();
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
        System.out.println("Se cierra la conexion: " + webSocketSession.getId());

        Set<Object> sectoresIds = redisService.getSectoresBySession(webSocketSession.getId());        
        
        redisService.removeSessionFromSectors(webSocketSession.getId(), sectoresIds);
        redisService.removeSectoresFromSession(webSocketSession.getId());

        webSocketService.removeSession(webSocketSession.getId());
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
