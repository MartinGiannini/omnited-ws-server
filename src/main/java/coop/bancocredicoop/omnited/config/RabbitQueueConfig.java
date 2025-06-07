package coop.bancocredicoop.omnited.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueConfig {
    
    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;
    
    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.colaDB_WS}")
    private String colaEntranteDB_WS;
    
    @Value("${spring.rabbitmq.colaLST_WS}")
    private String colaEntranteLST_WS;
    
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue colaEntranteDB_WS() {
        return new Queue(colaEntranteDB_WS, true); // Cola de entrada CR (duradera)
    }
    
    @Bean
    public Queue colaEntranteLST_WS() {
        return new Queue(colaEntranteLST_WS, true); // Cola de entrada CR (duradera)
    }

    /**
     * Binding de la cola de entrada desde Servidor DB
     * @param colaEntranteDB_WS
     * @param exchange
     * @return 
     */
    @Bean
    public Binding bindingColaEntranteDB_WS(Queue colaEntranteDB_WS, TopicExchange exchange) {
        return BindingBuilder.bind(colaEntranteDB_WS).to(exchange).with(routingKey + ".db_ws");
    }
    
    /**
     * Binding de la cola de entrada desde Servidor STR
     * @param colaEntranteLST_WS
     * @param exchange
     * @return 
     */
    @Bean
    public Binding bindingColaEntranteLST_WS(Queue colaEntranteLST_WS, TopicExchange exchange) {
        return BindingBuilder.bind(colaEntranteLST_WS).to(exchange).with(routingKey + ".lst_ws");
    }
}