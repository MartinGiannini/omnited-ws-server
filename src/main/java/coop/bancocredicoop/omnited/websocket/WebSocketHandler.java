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
                    JsonNode jsonPayloadNode = jsonNode.get("jsonPayload");

                    if (jsonPayloadNode.isTextual()) {
                        jsonPayloadNode = objectMapper.readTree(jsonPayloadNode.asText());
                    }

                    // Accedemos al array "sectores" dentro de "sectoresDatos"
                    JsonNode sectoresArray = jsonPayloadNode.get("sectoresDatos").get("sectores");

                    // Recorremos el array y extraemos cada "idSector"
                    for (JsonNode sector : sectoresArray) {
                        // Verificamos que el campo "idSector" existe y es numérico
                        if (sector.has("idSector") && sector.get("idSector").isInt()) {
                            redisService.addSessionToSectors(sector.get("idSector").asInt(), session.getId());
                            redisService.addIdSectorToSession(sector.get("idSector").asInt(), session.getId());
                        }
                    }
                    
                    Set<Object> ses = redisService.getSessionsBySector(1);
                    
                    ses.forEach(sessiona -> 
                            System.out.println("Session ID: " + sessiona)
                    );

                    break;
                case "usuariologingruposWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                case "usuarioHabilidadesWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                case "usuarioEstadosWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                case "permisosSupervisionWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                case "permisosOperacionWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                case "modificaColaWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                case "grupoHabilidadesWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                case "grupoEstadosWS":
                    // Guardo el hash en Redis: MENSAJEID - WEBSOCKETID
                    redisService.mapMessageIDToWebSocketSession(jsonNode.get("id").asText(), session.getId());

                    // Continuo procesando el mensaje del cliente
                    webSocketToRabbit.processMessage(message.getPayload());
                    break;
                default:
                    System.out.println("Llegó algo desconocido: ");
                    LOGGER.log(Level.INFO, "Esto:", new Object[]{session.getId(), message.getPayload()});
                    break;
            }
        } catch (IOException e) {
            LOGGER.log(Level.INFO, "Received message from {0}: {1}", e.getMessage());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, org.springframework.web.socket.CloseStatus status) throws Exception {
        System.out.println("Se cierra la conexion: "+webSocketSession.getId());
        
        Set<Object> sectoresIds = redisService.getSectoresBySession(webSocketSession.getId());
        
        redisService.removeSessionFromSectors(webSocketSession.getId(), sectoresIds);
        
        LOGGER.log(Level.INFO, "Connection closed: {0}", webSocketSession.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.log(Level.SEVERE, "Error in session {0}: {1}", new Object[]{session.getId(), exception.getMessage()});
        session.close();
    }
}
