package coop.bancocredicoop.omnited.service;

import java.time.Duration;
import java.util.Set;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Guardar la relación entre messageID y websocketSessionID
    public void mapMessageIDToWebSocketSession(String messageID, String webSocketSessionID) {
        redisTemplate.opsForValue().set("message:" + messageID, webSocketSessionID, Duration.ofMinutes(10));
    }

    // Obtener el websocketSessionID asociado a un messageID
    public String getWebSocketSessionByMessageID(String messageID) {
        return (String) redisTemplate.opsForValue().get("message:" + messageID);
    }

    // Eliminar la relación entre un messageID y su websocketSessionID
    public void removeMessageID(String messageID) {
        redisTemplate.delete("message:" + messageID);
    }

    // Eliminar todas las relaciones de un websocketSessionID (si es necesario en el futuro)
    public void deleteWebSocketSession(String webSocketSessionID) {
        // Buscar todas las claves relacionadas con este websocketSessionID (opcional)
        Set<String> keys = redisTemplate.keys("message:*");
        if (keys != null) {
            for (String key : keys) {
                String associatedSessionID = (String) redisTemplate.opsForValue().get(key);
                if (webSocketSessionID.equals(associatedSessionID)) {
                    redisTemplate.delete(key);
                }
            }
        }
    }
}