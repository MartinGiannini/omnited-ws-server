package coop.bancocredicoop.omnited.websocket;

import coop.bancocredicoop.omnited.service.WebSocketService;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketPingSender {

    private final WebSocketService webSocketService;

    public WebSocketPingSender(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Scheduled(fixedRate = 60000) // Envía un ping cada 30 segundos
    public void sendPingToSessions() {
        // Itera sobre las sesiones almacenadas en el ConcurrentHashMap
        for (WebSocketSession session : webSocketService.getAllSessions().values()) {
            if (session.isOpen()) {
                try {
                    ByteBuffer payload = ByteBuffer.wrap("ping".getBytes(StandardCharsets.UTF_8));
                    PingMessage pingMessage = new PingMessage(payload);
                    session.sendMessage(pingMessage);
                } catch (IOException e) {
                    // Manejo de excepción: podrías cerrar la sesión o registrar el error
                    System.err.println("Error enviando ping a la sesión " + session.getId() + ": " + e.getMessage());
                }
            }
        }
    }
}