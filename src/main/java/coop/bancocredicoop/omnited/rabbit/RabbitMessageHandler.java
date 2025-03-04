package coop.bancocredicoop.omnited.rabbit;

public interface RabbitMessageHandler {
    void handle(String idMensaje, String type, String jsonPayload, Integer idSector) throws Exception;
}