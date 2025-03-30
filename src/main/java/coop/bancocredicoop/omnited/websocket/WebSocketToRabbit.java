package coop.bancocredicoop.omnited.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.rabbit.RabbitSenderService;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public class WebSocketToRabbit {
    
    private static final Logger LOGGER = Logger.getLogger(WebSocketToRabbit.class.getName());

    private final ObjectMapper objectMapper;
    private final RabbitSenderService rabbitSenderService;

    public WebSocketToRabbit(
            ObjectMapper objectMapper,
            RabbitSenderService rabbitSenderService) {
        this.objectMapper = objectMapper;
        this.rabbitSenderService = rabbitSenderService;
    }

    public void processMessage(String rawMessage) {

        try {
            // Parsear el mensaje JSON recibido
            JsonNode jsonNode = objectMapper.readTree(rawMessage);
            String id = jsonNode.get("id").asText();

            String type = jsonNode.get("type").asText();
            String jsonPayload = objectMapper.writeValueAsString(jsonNode.get("jsonPayload"));

            System.out.println("El type que llega es: "+type);
            
            
            // Enviar el mensaje a RabbitMQ
            rabbitSenderService.sendMessage(id, type, jsonPayload);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing WebSocket message", e);
        }
    }
}
