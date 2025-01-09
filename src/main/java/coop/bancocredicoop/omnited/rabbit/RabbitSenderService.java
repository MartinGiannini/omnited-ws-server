package coop.bancocredicoop.omnited.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.service.RedisService;
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
    private final RedisService redisService;

    public RabbitSenderService(RabbitTemplate rabbitTemplate, RedisService redisService) {
        this.rabbitTemplate = rabbitTemplate;
        this.redisService = redisService;
    }

    /**
     * Enviar un mensaje a RabbitMQ.
     *
     * @param id Identificador del mensaje.
     * @param type
     * @param jsonPayload
     */
    public void sendMessage(String id, String type, String jsonPayload) {
        MensajeJSON.Builder messageBuilder = MensajeJSON.newBuilder()
                .setId(id)
                .setType(type)
                .setJsonPayload(jsonPayload);
        
        MensajeJSON message = messageBuilder.build();
        
        rabbitTemplate.convertAndSend(exchangeName, routingKeyOut, message);
    }

    public static class Recipient {

        private final String recipientId;
        private final String recipientInfo;

        public Recipient(String recipientId, String recipientInfo) {
            this.recipientId = recipientId;
            this.recipientInfo = recipientInfo;
        }

        public String getRecipientId() {
            return recipientId;
        }

        public String getRecipientInfo() {
            return recipientInfo;
        }
    }
}
