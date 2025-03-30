package coop.bancocredicoop.omnited.service;

import java.time.Duration;
import java.util.Set;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(
            @Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplateString
    ) {
        this.redisTemplate = redisTemplateString;
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
        redisTemplate.opsForValue().set(messageID, webSocketSessionID, Duration.ofMinutes(1));
    }

    /**
     * Obtiene el websocketSessionID asociado a un messageID
     *
     * @param messageID
     * @return
     */
    public String temporalGetWebSocketSessionByMessageID(String messageID) {
        return (String) redisTemplate.opsForValue().get(messageID);
    }

    /**
     * Elimina la relación entre un messageID y su websocketSessionID Los
     * mensajes temporales se borran en n minutos. Sin embargo dejo el método
     * para borrarlo manualmente.
     *
     * @param messageID
     */
    public void temporalRemoveMessageID(String messageID) {
        redisTemplate.delete(messageID);
    }
    /**
     * FIN TEMPORALES
     */
    
    /**
     * Asocia websocketSessionID y sectores de la siguiente forma:
     *
     * sector:1 -> websocket1, websocket2, websocket3;
     * sector:2 -> websocket3, websocket4;
     * 
     * session:websocket1 -> idSector1;
     * session:websocket3 -> idSector1, idSector2
     * 
     *
     * @param webSocketSessionId
     * @param idPerfil
     * @param idSectores
     */
    public void addSessionAndSectors(String webSocketSessionId, Integer idPerfil, Set<Integer> idSectores) {
        String key = "sector:";
        String sessionKey = "session:" + webSocketSessionId;
        switch (idPerfil) {
            case 1:
                idSectores.forEach(idSector -> {
                    redisTemplate.opsForSet().add(key + idSector, webSocketSessionId);
                    redisTemplate.opsForSet().add(sessionKey, idSector);
                });
                break;
            case 2:
                idSectores.forEach(idSector -> {
                    redisTemplate.opsForSet().add(key + idSector, webSocketSessionId);
                    redisTemplate.opsForSet().add(sessionKey, idSector);
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
     * @param idSector
     * @return
     */
    public Set<Object> getSessionsBySector(Integer idSector) {
        String key = "sector:";
        return redisTemplate.opsForSet().members(key + idSector);
    }

    /**
     * Elimina un websocketid de un sector determinado.
     *
     * @param webSocketSessionId
     * @param idSector
     */
    public void removeSessionFromSector(String webSocketSessionId, Integer idSector) {
        String key = "sector:";
        redisTemplate.opsForSet().remove(key + idSector, webSocketSessionId);
    }

    /**
     * Eliminar un websocketid de todos los sectores que contengan el
     * websocketid
     *
     * @param webSocketSessionId
     * @param sectores
     */
    public void removeSessionFromSectors(String webSocketSessionId, Set<Object> sectores) {
        sectores.forEach(sectorObj -> {
            String sectorKey = "sector:" + sectorObj.toString();
            redisTemplate.opsForSet().remove(sectorKey, webSocketSessionId);
        });
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
        redisTemplate.opsForSet().add(webSocketSessionId, idSector);
    }

    /**
     * Obtengo todos los sectores asociados al webSocketSessionId Ejemplo:
     *
     * idSector1 -> websocket1, websocket2, websocket3 idSector2 -> websocket3,
     * websocket4 Si busco el websocket3 me devolverá => idSector1, idSector2
     *
     * @param webSocketSessionId
     * @return
     */
    public Set<Object> getSectoresBySession(String webSocketSessionId) {
        return redisTemplate.opsForSet().members("session:" + webSocketSessionId);
    }

    /**
     * Elimina la KEY del WebSocketID que mapea los sectoresIds
     *
     * @param webSocketSessionId
     */
    public void removeSectoresFromSession(String webSocketSessionId) {
        redisTemplate.delete(webSocketSessionId);
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
