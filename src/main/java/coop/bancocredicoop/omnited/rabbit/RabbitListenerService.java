package coop.bancocredicoop.omnited.rabbit;

import coop.bancocredicoop.omnited.config.MessageOut.MensajeJSON;
import coop.bancocredicoop.omnited.rabbit.handler.EnvioBroadcastHandler;
import coop.bancocredicoop.omnited.rabbit.handler.EnvioUnicastHandler;
import coop.bancocredicoop.omnited.rabbit.handler.EnvioTemporalHandler;
import coop.bancocredicoop.omnited.rabbit.handler.UsuarioSessionHandler;
import coop.bancocredicoop.omnited.service.RedisService;
import coop.bancocredicoop.omnited.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class RabbitListenerService {

    private final Map<String, RabbitMessageHandler> handlers = new HashMap<>();

    // Constructor con inyección de dependencia
    public RabbitListenerService(
            WebSocketService webSocketService,
            RedisService redisService
    ) {
        // Handlers para Login
        handlers.put("usuariologinDB", new EnvioTemporalHandler(redisService, webSocketService));
        handlers.put("usuariologinsectoresDB", new EnvioTemporalHandler(redisService, webSocketService));
        handlers.put("usuariologinpermisosDB", new EnvioTemporalHandler(redisService, webSocketService));
        handlers.put("usuariologingruposDB", new EnvioTemporalHandler(redisService, webSocketService));
        handlers.put("usuariologinestrategiasDB", new EnvioTemporalHandler(redisService, webSocketService));
        handlers.put("usuariosessionDB", new UsuarioSessionHandler(redisService));
                
        // Handlers para Modificacion Broadcast
        handlers.put("usuarioHabilidadesSectorDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("usuarioEstadosSectorDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("usuarioPermisosOperacionSectorDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("usuarioPermisosSupervisionSectorDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("grupoHabilidadesAgregaDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("grupoHabilidadesModificaDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("grupoEstadosAgregaDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("grupoEstadosModificaDB", new EnvioBroadcastHandler(redisService, webSocketService));
        
        handlers.put("colaActualizarDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("colaAgregarDB", new EnvioBroadcastHandler(redisService, webSocketService));
        
        handlers.put("usuarioActualizarDB", new EnvioBroadcastHandler(redisService, webSocketService));
        handlers.put("usuarioAgregarDB", new EnvioBroadcastHandler(redisService, webSocketService));
        
        // Handlers para Modificacion Unicast
        handlers.put("usuarioHabilidadesUsuarioDB", new EnvioUnicastHandler(redisService, webSocketService));
        handlers.put("usuarioEstadosUsuarioDB", new EnvioUnicastHandler(redisService, webSocketService));
        handlers.put("usuarioPermisosOperacionUsuarioDB", new EnvioUnicastHandler(redisService, webSocketService));
        handlers.put("usuarioPermisosSupervisionUsuarioDB", new EnvioUnicastHandler(redisService, webSocketService));
        
        // Handler para confirmacion de Modificacion
        handlers.put("cambiosRealizadosDB", new EnvioTemporalHandler(redisService, webSocketService));
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
            Integer idSector = message.getIdSector();
            
            
            /**
             * Pueden llegar 3 tipos de mensajes: 
             * 1) Mensajes de respuesta al request del cliente 
             * 2) Mensajes que parten del núcleo del sistema para 1 cliente. 
             * 3) Mensajes Broadcast que parten del núcleo del sistema para n clientes.
             */
            RabbitMessageHandler handler = handlers.get(mensajeType);

            if (handler != null) {
                handler.handle(idMensaje, mensajeType, mensajeJson, idSector);
            } else {

                System.out.println("El handler vino NULL!!! WTF??");

            }

        } catch (Exception e) {
            System.err.println("ERROR A DETECTAR" + e);
        }
    }
}
