package com.politecnico.edu.inventario.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.url}")
    private String url;

    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Value("${spring.rabbitmq.username}")
    private String user;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.ssl.enabled}")
    private Boolean ssl;

    @Value("${inventary.rabbitmq.queue}")
    private String inventoryQueue;

    @Bean
    public RabbitConnectionFactoryBean rabbitConnectionFactoryBean() {
        var factoryBean = new RabbitConnectionFactoryBean();
        factoryBean.setPassword(password);
        factoryBean.setHost(url);
        factoryBean.setUsername(user);
        factoryBean.setVirtualHost("/");
        factoryBean.setPort(port);
        factoryBean.setUseSSL(ssl);
        return factoryBean;
    }

    @Bean
    public ConnectionFactory rabbitConnectionFactory(
            RabbitConnectionFactoryBean abstractConnectionFactory) throws Exception {
        return new CachingConnectionFactory(Objects.requireNonNull(abstractConnectionFactory.getObject()));
    }

    @Bean
    public RabbitTemplate createTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue inventoryQueue() {
        return new Queue(inventoryQueue, false);
    }
}
