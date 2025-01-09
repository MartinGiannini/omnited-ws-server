package coop.bancocredicoop.omnited.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Extrae el usuario del mensaje JSON recibido.
     * 
     * @param jsonString El mensaje JSON como cadena.
     * @return El nombre de usuario si se encuentra, de lo contrario, devuelve null.
     */
    public static String extractUsuario(String jsonString) {
        try {
            JsonNode rootNode = objectMapper.readTree(jsonString);

            // Acceder a jsonPayload -> UsuarioDatos -> usuario
            return rootNode
                    .path("jsonPayload")
                    .path("UsuarioDatos")
                    .path("usuario")
                    .asText(null); // null si no encuentra el campo
        } catch (JsonProcessingException e) {
            // Manejar la excepción en caso de que el JSON no sea válido
            System.err.println("Error al procesar el JSON: " + e.getMessage());
            return null;
        }
    }
}
