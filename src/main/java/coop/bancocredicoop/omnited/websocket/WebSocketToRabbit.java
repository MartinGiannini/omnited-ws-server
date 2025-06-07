package coop.bancocredicoop.omnited.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.rabbit.RabbitSenderService;
import java.util.logging.Level;
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

            LOGGER.log(Level.INFO, "============================================================");
            LOGGER.log(Level.INFO, "============== Se procesa el mensaje entrante ==============");
            LOGGER.log(Level.INFO, "============== Llega un TYPE: " + type + "==================");

            /*
            if (type.equals("botWhatsAppNuevoWS") || type.equals("botWhatsAppActualizarWS")) {
                // Enviar el mensaje a RabbitMQ
                rabbitSenderService.sendMessageToWA(id, type, jsonPayload);
            }
            */
            // Enviar el mensaje a RabbitMQ
            rabbitSenderService.sendMessageToDB(id, type, jsonPayload);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing WebSocket message", e);
        }
    }

    public void processMessageSelfOriginated(String idMensaje, String mensajeType, String mensajeJson) {

        try {

            // Usar el servicio RabbitSenderService para enviar el mensaje
            rabbitSenderService.sendMessageToDB(idMensaje, mensajeType, mensajeJson);

        } catch (Exception e) {
            System.out.println("Error en el processMessage" + e);
        }
    }
}
