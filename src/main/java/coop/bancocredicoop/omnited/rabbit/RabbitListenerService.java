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
import java.util.Set;
import org.springframework.web.socket.TextMessage;

@Service
public class RabbitListenerService {

    private final WebSocketService webSocketService;
    private final RedisService redisService;
    private final Map<String, RabbitMessageHandler> handlers = new HashMap<>();

    // Constructor con inyección de dependencia
    public RabbitListenerService(
            WebSocketService webSocketService,
            RedisService redisService
    ) {
        handlers.put("usuariologinDB", new UsuarioLoginHandler(redisService, webSocketService));
        handlers.put("usuariologinpermisosDB", new UsuarioLoginHandler(redisService, webSocketService));
        handlers.put("usuariologinsectoresDB", new UsuarioLoginHandler(redisService, webSocketService));
        handlers.put("usuariologingruposDB", new UsuarioLoginHandler(redisService, webSocketService));
        handlers.put("usuariologinestrategiasDB", new UsuarioLoginHandler(redisService, webSocketService));
        handlers.put("cambiosRealizadosDB", new UsuarioLoginHandler(redisService, webSocketService));
        this.webSocketService = webSocketService;
        this.redisService = redisService;
    }

    @RabbitListener(queues = {
        "#{@environment.getProperty('spring.rabbitmq.colaDB')}",
        "#{@environment.getProperty('spring.rabbitmq.colaCR')}"
    })

    public void receiveMessage(MensajeJSON message) {

        try {

            String idMensaje = message.getIdMensaje();
            String mensajeType = message.getMensajeType();
            String mensajeJson = message.getMensajeJson();
            int idSector = message.getIdSector();

            /**
             * Pueden llegar 3 tipos de mensajes: 1) Mensajes de respuesta al
             * request del cliente 2) Mensajes que parten del núcleo del sistema
             * para 1 cliente. 3) Mensajes Broadcast que parten del núcleo del
             * sistema para n clientes.
             */
            RabbitMessageHandler handler = handlers.get(mensajeType);

            if (handler != null) {
                handler.handle(idMensaje, mensajeType, mensajeJson);
            } else {
                
                System.out.println("El tipo de mensaje que estoy enviadno es: "+mensajeType);

                Set<Object> webSocketSessionIds = redisService.getSessionsBySector(idSector);

                webSocketSessionIds.forEach(wssId -> {

                    WebSocketSession session = webSocketService.getSession(wssId.toString());
                    if (session != null && session.isOpen()) {
                        try {
                            session.sendMessage(new TextMessage(mensajeType + ";" + mensajeJson));
                        } catch (IOException e) {
                            System.err.println("Error al enviar mensaje al cliente: " + e.getMessage());
                        }
                    } else {
                        System.err.println("La sesión no está disponible o está cerrada para el cliente con ID: " + idMensaje);
                    }

                });

            }

        } catch (Exception e) {
            System.err.println("ERROR A DETECTAR" + e);
        }
    }
}
