package com.imagina.productsservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    private final CachingConnectionFactory cachingConnectionFactory;

    public QueueConfig(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
    public Declarables createPurchasesSchema() {
        return new Declarables(
                new FanoutExchange("x.post-purchases"),
                new Queue("q.send-email", false),
                new Queue("q.send-sms", false),
                new Binding(
                        "q.send-email",
                        Binding.DestinationType.QUEUE,
                        "x.post-purchases",
                        "send-email",
                        null
                ),
                new Binding(
                        "q.send-sms",
                        Binding.DestinationType.QUEUE,
                        "x.post-purchases",
                        "send-sms",
                        null
                )
        );
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        return rabbitTemplate;
    }
}
