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
        //LOGGER.log(Level.INFO, "Received message from {0}: {1}", new Object[]{session.getId(), message.getPayload()});

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(message.getPayload());

            String type = jsonNode.get("type").asText();

            if ("ping".equals(type)) {
                session.sendMessage(new TextMessage("ping;" + pong));
            } else {

                // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                redisService.temporalMapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());
                switch (type) {
                    case "usuariologinWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    case "usuarioadminWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    case "usuarioHabilidadesWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    case "usuarioEstadosWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    case "permisosSupervisionWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    case "permisosOperacionWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    case "colaadminWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    case "grupoHabilidadesWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    case "grupoEstadosWS":
                        // Continuo procesando el mensaje del cliente
                        webSocketToRabbit.processMessage(message.getPayload());
                        break;
                    default:
                        System.out.println("Llegó algo desconocido: ");
                        LOGGER.log(Level.INFO, "Esto:", new Object[]{session.getId(), message.getPayload()});
                        break;
                }

            }
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Received message from {0}: {1}", e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, org.springframework.web.socket.CloseStatus status) throws Exception {
        System.out.println("Se cierra la conexion: " + webSocketSession.getId());

        //Set<Object> sectoresIds = redisService.getSectoresBySession(webSocketSession.getId());

        //redisService.removeSessionFromSectors(webSocketSession.getId(), sectoresIds);
        //redisService.removeSectoresFromSession(webSocketSession.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.log(Level.SEVERE, "Error in session {0}: {1}", new Object[]{session.getId(), exception.getMessage()});
        session.close();
    }
}
