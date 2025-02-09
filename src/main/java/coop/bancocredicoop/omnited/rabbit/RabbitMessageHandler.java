package coop.bancocredicoop.omnited.rabbit;

public interface RabbitMessageHandler {
    void handle(String jsonPayload, String id) throws Exception;
}