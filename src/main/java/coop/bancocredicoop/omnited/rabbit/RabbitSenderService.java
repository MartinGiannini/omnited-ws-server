package coop.bancocredicoop.omnited.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class RabbitSenderService {

    @Value("${spring.rabbitmq.routing-key}.ws_db")
    private String routingKeyOutWS_DB;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    private final RabbitTemplate rabbitTemplate;

    public RabbitSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    
    /**
     * Enviar un mensaje por RabbitMQ al servicio de DB.
     *
     * @param idMensaje
     * @param mensajeType
     * @param mensajeJson
     */
    public void sendMessageToDB(String idMensaje, String mensajeType, String mensajeJson) {
        MensajeJSON.Builder messageBuilder = MensajeJSON.newBuilder()
                .setIdMensaje(idMensaje)
                .setMensajeType(mensajeType)
                .setMensajeJson(mensajeJson)
                .setFechaEnvio(System.currentTimeMillis());
        
        MensajeJSON message = messageBuilder.build();
        
        rabbitTemplate.convertAndSend(exchangeName, routingKeyOutWS_DB, message);
    }
}