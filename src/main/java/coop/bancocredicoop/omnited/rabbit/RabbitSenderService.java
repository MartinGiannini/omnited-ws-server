package coop.bancocredicoop.omnited.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class RabbitSenderService {

    @Value("${spring.rabbitmq.routing-key}.ws")
    private String routingKeyOut;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    private final RabbitTemplate rabbitTemplate;

    public RabbitSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Enviar un mensaje a RabbitMQ.
     *
     * @param idMensaje
     * @param mensajeType
     * @param mensajeJson
     */
    public void sendMessage(String idMensaje, String mensajeType, String mensajeJson) {
        MensajeJSON.Builder messageBuilder = MensajeJSON.newBuilder()
                .setIdMensaje(idMensaje)
                .setMensajeType(mensajeType)
                .setMensajeJson(mensajeJson);
        
        MensajeJSON message = messageBuilder.build();
        
        rabbitTemplate.convertAndSend(exchangeName, routingKeyOut, message);
    }
}