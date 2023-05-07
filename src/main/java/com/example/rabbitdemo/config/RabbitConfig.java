package com.example.rabbitdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.rabbitdemo.controller.MessageController.EXCHANGE;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String QUEUE = "spring-queue";
    public static final String EXTERNAL_QUEUE = "external-queue";

    @Bean
    public Queue springQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Queue externalQueue() {
        return new Queue(EXTERNAL_QUEUE);
    }

    @Bean
    public Exchange messageExchangeFanout() {
        return new FanoutExchange(EXCHANGE);
    }

    @Bean
    public Binding springQueueBinding(){
        return BindingBuilder
            .bind(springQueue())
            .to(messageExchangeFanout())
            .with("")
            .noargs();
    }

    @Bean
    public Binding externalQueueBinding(){
        return BindingBuilder
            .bind(externalQueue())
            .to(messageExchangeFanout())
            .with("")
            .noargs();
    }
}
