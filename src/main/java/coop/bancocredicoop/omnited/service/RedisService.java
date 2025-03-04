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

    /**
     * INICIA TEMPORALES
     */
    
    /**
     *
     * Guarda la relación entre messageID y websocketSessionID. Este método se
     * usa solo para el LOGIN de usuario.
     *
     * Ejemplo de guardado:
     *
     * message: b7856c9c-9986-4360-aad4-95a33b598a43 =>
     * 6db454f7-b007-f108-46c6-115dcf760512, 1 minutos UUID WSID TIEMPO DE VIDA
     *
     * @param messageID
     * @param webSocketSessionID
     */
    public void temporalMapMessageIDToWebSocketSession(String messageID, String webSocketSessionID) {
        redisTemplate.opsForValue().set("message:" + messageID, webSocketSessionID, Duration.ofMinutes(1));
    }

    /**
     * Obtiene el websocketSessionID asociado a un messageID
     *
     * @param messageID
     * @return
     */
    public String temporalGetWebSocketSessionByMessageID(String messageID) {
        return (String) redisTemplate.opsForValue().get("message:" + messageID);
    }
    
    /**
     * Elimina la relación entre un messageID y su websocketSessionID Los
     * mensajes temporales se borran en n minutos. Sin embargo dejo el método
     * para borrarlo manualmente.
     *
     * @param messageID
     */
    public void temporalRemoveMessageID(String messageID) {
        redisTemplate.delete("message:" + messageID);
    }
    
    /**
     * FIN TEMPORALES
     */

    /**
     * Elimina todas las relaciones de un websocketSessionID (si es necesario en
     * el futuro)
     *
     * @param webSocketSessionID
     */
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
    public void addSessionToSectors(String webSocketSessionId, Integer idPerfil, Set<Integer> idSectores) {
        switch (idPerfil) {
            case 1:
                idSectores.forEach(idSector -> {
                    String key = "sector:" + idSector;
                    redisTemplate.opsForSet().add(key, webSocketSessionId);
                });
                break;
            case 2:
                idSectores.forEach(idSector -> {
                    String key = "sector:" + idSector;
                    redisTemplate.opsForSet().add(key, webSocketSessionId);
                });
                break;
            case 3:
                // Es operador, no se guarda.
                break;
            default:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Que fucking llego????? " + idPerfil);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                break;
        }
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
     * Asocia todos los idSector al websocketSessionId. Esto lo hago para que
     * cuando se cierra el Websocket se busque todos los idSector asociados y
     * asi eliminar por IdSector el websocketSessionId
     *
     * Ejemplo de o que guarda: 43047ed1-a2d3-2602-93ef-12f7bfebfa21 -> [1,2,3]
     *
     * @param idSector
     * @param webSocketSessionId
     */
    public void addIdSectorToSession(Integer idSector, String webSocketSessionId) {
        String key = "websocket:" + webSocketSessionId;
        redisTemplate.opsForSet().add(key, idSector);
    }

    /**
     * Obtengo todos los sectores asociados al webSocketSessionId
     *
     * @param webSocketSessionId
     * @return
     */
    public Set<Object> getSectoresBySession(String webSocketSessionId) {
        String key = "websocket:" + webSocketSessionId;
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * Elimina el websocketSessionID del Set asociado a cada idSector. Al final
     * elimino el WebSocket de la DB Redis
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

    /**
     * Elimina la KEY del WebSocketID que mapea los sectoresIds
     *
     * @param webSocketSessionId
     */
    public void removeSectoresFromSession(String webSocketSessionId) {
        String key = "websocket:" + webSocketSessionId;
        redisTemplate.delete(key);
    }
    
    public void usuariosMapUsuarioIDToWebSocketSession(Integer idUsuario, String webSocketSessionID) {
        redisTemplate.opsForValue().set("usuario:" + idUsuario, webSocketSessionID, Duration.ofMinutes(1));
    }
    
    public String usuariosGetWebSocketSessionByIdUsuario(Integer idUsuario) {
        return (String) redisTemplate.opsForValue().get("usuario:" + idUsuario);
    }

    public void usuariosRemoveIdUsuario(Integer idUsuario) {
        redisTemplate.delete("usuario:" + idUsuario);
    }
}
