package coop.bancocredicoop.omnited.rabbit.handler;

import coop.bancocredicoop.omnited.rabbit.RabbitMessageHandler;
import coop.bancocredicoop.omnited.service.RedisService;
import coop.bancocredicoop.omnited.service.WebSocketService;
import java.io.IOException;
import java.util.Set;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class EnvioMulticastHandler implements RabbitMessageHandler {

    private final RedisService redisService;
    private final WebSocketService webSocketService;

    public EnvioMulticastHandler(
            RedisService redisService,
            WebSocketService connectionManager
    ) {
        this.redisService = redisService;
        this.webSocketService = connectionManager;
    }

    @Override
    public void handle(String idMensaje, String type, String jsonPayload, Integer idSector) throws Exception {

        System.out.println("Llega el operadorStream");
        
        Set<Object> wbSessionIDs = redisService.getSessionsBySector(idSector);
        
        System.out.println("Los ids que estan asociados al sector: "+idSector+" son: ");
        wbSessionIDs.forEach(id -> {
            System.out.println(id);
        });
        
        wbSessionIDs.forEach(wbSessionId -> {
            
            WebSocketSession session = webSocketService.getSession(wbSessionId.toString());

            if (session != null && session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(type + ";" + jsonPayload));
                } catch (IOException e) {
                    System.err.println("Error al enviar mensaje al cliente: " + e.getMessage());
                }
            } else {
                System.out.println("********");
                System.out.println("El type de este mensaje es: "+type);
                System.err.println("La sesión no está disponible o está cerrada para el cliente con ID: " + wbSessionId);
                System.out.println("********");
            }
        });
    }
}
