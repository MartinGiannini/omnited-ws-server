package coop.bancocredicoop.omnited.websocket;

import coop.bancocredicoop.omnited.service.RedisService;
import coop.bancocredicoop.omnited.service.WebSocketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketToRabbit webSocketToRabbit;
    private final WebSocketService webSocketService;
    private final RedisService redisService;

    public WebSocketConfig(WebSocketToRabbit webSocketToRabbit, WebSocketService webSocketService, RedisService redisService) {
        this.webSocketToRabbit = webSocketToRabbit;
        this.webSocketService = webSocketService;
        this.redisService = redisService;
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketHandler(webSocketToRabbit, webSocketService, redisService);
    }

    @Bean
    @Primary
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        scheduler.setThreadNamePrefix("sockjs-");
        scheduler.initialize();
        return scheduler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler(), "/ws").setAllowedOrigins("*");
    }
}