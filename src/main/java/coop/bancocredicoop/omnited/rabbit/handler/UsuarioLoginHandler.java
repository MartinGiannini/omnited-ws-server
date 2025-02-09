package coop.bancocredicoop.omnited.rabbit.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import coop.bancocredicoop.omnited.rabbit.RabbitMessageHandler;
import coop.bancocredicoop.omnited.service.RedisService;

public class UsuarioLoginHandler implements RabbitMessageHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RedisService redisService;

    public UsuarioLoginHandler(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void handle(String jsonPayload, String idMensaje) throws Exception {
        
        // Parsear el JSON raíz
        JsonNode rootNode = objectMapper.readTree(jsonPayload);
        // Acceder al nodo "UsuarioDatos"
        
        System.out.println("El mnsaje que vuelve es:");
        System.out.println("----------------------");
        System.out.println(""+jsonPayload);
        
    }
}