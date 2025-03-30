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

    /*
    @Value("${spring.rabbitmq.colaSalida}")
    private String colaSalida;
    */
    
    @Value("${spring.rabbitmq.colaDBU}")
    private String colaEntranteDBU;
    
    @Value("${spring.rabbitmq.colaDBM}")
    private String colaEntranteDBM;

    @Value("${spring.rabbitmq.colaLIST}")
    private String colaEntranteLIST;

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /*
    @Bean
    public Queue colaSalida() {
        return new Queue(colaSalida, true); // Cola de salida (duradera)
    }
    */
    
    @Bean
    public Queue colaEntranteDBU() {
        return new Queue(colaEntranteDBU, true); // Cola de entrada DB (duradera)
    }
    
    @Bean
    public Queue colaEntranteDBM() {
        return new Queue(colaEntranteDBM, true); // Cola de entrada DB (duradera)
    }

    @Bean
    public Queue colaEntranteLIST() {
        return new Queue(colaEntranteLIST, true); // Cola de entrada CR (duradera)
    }

    /**
     * Binding de la cola de salida al exchange con la clave de enrutamiento.
     * @param colaSalida
     * @param exchange
     * @return 
     */
    /*
    @Bean
    public Binding bindingColaSalida(Queue colaSalida, TopicExchange exchange) {
        return BindingBuilder.bind(colaSalida).to(exchange).with(routingKey + ".ws");
    }
    */
    
    /**
     * Binding de la cola de entrada DB al exchange con una clave de enrutamiento específica.
     * @param colaEntranteDBU
     * @param exchange
     * @return 
     */
    @Bean
    public Binding bindingColaEntranteDBU(Queue colaEntranteDBU, TopicExchange exchange) {
        return BindingBuilder.bind(colaEntranteDBU).to(exchange).with(routingKey + ".dbu");
    }
    
    /**
     * Binding de la cola de entrada DB al exchange con una clave de enrutamiento específica.
     * @param colaEntranteDBM
     * @param exchange
     * @return 
     */
    @Bean
    public Binding bindingColaEntranteDBM(Queue colaEntranteDBM, TopicExchange exchange) {
        return BindingBuilder.bind(colaEntranteDBM).to(exchange).with(routingKey + ".dbm");
    }

    /**
     * Binding de la cola de entrada CR al exchange con una clave de enrutamiento específica.
     * @param colaEntranteLIST
     * @param exchange
     * @return 
     */
    @Bean
    public Binding bindingColaEntranteLIST(Queue colaEntranteLIST, TopicExchange exchange) {
        return BindingBuilder.bind(colaEntranteLIST).to(exchange).with(routingKey + ".list");
    }
}