package coop.bancocredicoop.omnited.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.rabbit.handler.UsuarioLoginHandler;
import coop.bancocredicoop.omnited.service.RedisService;
import coop.bancocredicoop.omnited.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.socket.TextMessage;

@Service
public class RabbitListenerService {

    private final WebSocketService connectionManager;
    private final RedisService redisService;
    private final Map<String, RabbitMessageHandler> handlers = new HashMap<>();

    // Constructor con inyección de dependencia
    public RabbitListenerService(
            WebSocketService connectionManager, RedisService redisService
    ) {
        this.connectionManager = connectionManager;
        this.redisService = redisService;
        handlers.put("usuariologinDB", new UsuarioLoginHandler(redisService));
    }

    @RabbitListener(queues = {
        "#{@environment.getProperty('spring.rabbitmq.colaDB')}",
        "#{@environment.getProperty('spring.rabbitmq.colaCR')}"
    })

    public void receiveMessage(MensajeJSON message) {

        try {

            String idMensaje = message.getId();
            String type = message.getType();
            String jsonPayload = message.getJsonPayload();

            /**
             * Pueden llegar 3 tipos de mensajes: 1) Mensajes de respuesta al
             * request del cliente 2) Mensajes que parten del núcleo del sistema
             * para 1 cliente. 3) Mensajes Broadcast que parten del núcleo del
             * sistema para n clientes.
             *
             */
            System.out.println("*******************************************");
            System.out.println("RabbitListenerService: ID: " + message.getId());
            System.out.println("RabbitListenerService: TYPE: " + message.getType());
            System.out.println("RabbitListenerService: RC: " + message.getRecipients());
            System.out.println("*******************************************");

            // Identificar y procesar el mensaje según su tipo
            /*
            RabbitMessageHandler handler = handlers.get(type);

            // Proceso el mensaje de vuelta para guardar datos en Redis.
            if (handler != null) {
                handler.handle(jsonPayload, idMensaje);
            } else {
                System.err.println("No handler found for type: " + type);
            }
            */
            // Obtendo el ID de la base Redis por el usuario destino
            //String wbSessionID = redisService.getWebSocketByUser(message.getRecipients());
            // Obtengo el ID de la base Redis por el ID de usuario
            String wbSessionID = redisService.getWebSocketSessionByMessageID(message.getId());

            //String wbSessionID = redisService.getWebSocketByMessageId(message.getId());
            System.out.println("el ID es: " + wbSessionID);

            // Obtengo la Session de WebSocket de la caché
            WebSocketSession session = connectionManager.getSession(wbSessionID);

            if (session != null && session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message.getType() + ";" + message.getJsonPayload()));
                } catch (IOException e) {
                    System.err.println("Error al enviar mensaje al cliente: " + e.getMessage());
                }
            } else {
                System.err.println("La sesión no está disponible o está cerrada para el cliente con ID: " + message.getId());
            }

        } catch (Exception e) {
            System.err.println("ERROR A DETECTAR");
        }
    }
}