package coop.bancocredicoop.omnited.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.service.RedisService;
import coop.bancocredicoop.omnited.service.WebSocketService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = Logger.getLogger(WebSocketHandler.class.getName());
    private final WebSocketToRabbit webSocketToRabbit;
    private final WebSocketService webSocketService;
    private final RedisService redisService;
    private final String pong = "{\"ping\":\"pong\"}";

    // Constructor con inyección de dependencia
    public WebSocketHandler(WebSocketToRabbit webSocketToRabbit, WebSocketService webSocketService, RedisService redisService) {
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
        LOGGER.log(Level.INFO, "Received message from {0}: {1}", new Object[]{session.getId(), message.getPayload()});

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message.getPayload());

            String type = jsonNode.get("type").asText();

            switch (type) {
                case "ping":
                    session.sendMessage(new TextMessage("ping;" + pong));
                    break;
                case "usuariologinWS":

                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;

                case "usuariologinsectoresWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                    
                case "usuariologingruposWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;

                default:
                    LOGGER.log(Level.INFO, "Received message from {0}: {1}", new Object[]{session.getId(), message.getPayload()});
                    break;
            }
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Received message from {0}: {1}", e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        LOGGER.log(Level.INFO, "Connection closed: {0}", session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.log(Level.SEVERE, "Error in session {0}: {1}", new Object[]{session.getId(), exception.getMessage()});
        session.close();
    }
}
