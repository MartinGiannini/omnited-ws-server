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
        redisTemplate.opsForValue().set("message:" + messageID, webSocketSessionID, Duration.ofMinutes(1));
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

    /**
     * Asocia el websocketSessionID a cada uno de los idSector indicados.
     *
     * @param webSocketSessionId
     * @param idSector
     */
    public void addSessionToSectors(Integer idSector, String webSocketSessionId) {
        String key = "sector:" + idSector;
        redisTemplate.opsForSet().add(key, webSocketSessionId);
    }
    
    /**
     * Obtiene todos los websocketSessionIDs asociados a un idSector.
     *
     * @param sectorId
     * @return
     */
    public Set<Object> getSessionsBySector(Integer sectorId) {
        String key = "sector:" + sectorId;
        return redisTemplate.opsForSet().members(key);
    }
    
    /**
     * Asocia todos los idSector al websocketSessionId.
     * Esto lo hago para que cuando se cierra el Websocket se busque todos los
     * idSector asociados y asi eliminar por IdSector el websocketSessionId
     * 
     * Ejemplo de o que guarda:
     * 43047ed1-a2d3-2602-93ef-12f7bfebfa21 -> [1,2,3]
     * 
     * @param idSector
     * @param webSocketSessionId
     */
    public void addIdSectorToSession(Integer idSector, String webSocketSessionId) {
        redisTemplate.opsForSet().add(webSocketSessionId, idSector);
    }

    /**
     * Obtengo todos los sectores asociados al webSocketSessionId
     * 
     * @param webSocketSessionId
     * @return 
     */
    public Set<Object> getSectoresBySession(String webSocketSessionId) {
        return redisTemplate.opsForSet().members(webSocketSessionId);
    }

    /**
     * Elimina el websocketSessionID del Set asociado a cada idSector.
     * Al final elimino el WebSocket de la DB Redis
     *
     * @param sessionId
     * @param sectorIds
     */
    public void removeSessionFromSectors(String sessionId, Set<Object> sectorIds) {
        for (Object sectorId : sectorIds) {
            String key = "sector:" + sectorId;
            redisTemplate.opsForSet().remove(key, sessionId);
        }
        redisTemplate.delete(sessionId);
    }
    
}
