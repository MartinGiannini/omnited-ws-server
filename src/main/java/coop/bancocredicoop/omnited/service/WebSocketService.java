package coop.bancocredicoop.omnited.service;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
public class WebSocketService {
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    // Agregar una conexión
    public void addSession(String id, WebSocketSession session) {
        sessions.put(id, session);
    }

    // Obtener una conexión
    public WebSocketSession getSession(String id) {
        return sessions.get(id);
    }

    // Eliminar una conexión
    public void removeSession(String id) {
        sessions.remove(id);
    }

    // Verificar si una conexión existe
    public boolean hasSession(String id) {
        return sessions.containsKey(id);
    }

    // Obtener todas las conexiones
    public ConcurrentHashMap<String, WebSocketSession> getAllSessions() {
        return sessions;
    }
}
