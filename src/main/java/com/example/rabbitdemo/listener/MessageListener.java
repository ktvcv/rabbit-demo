package com.example.rabbitdemo.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.rabbitdemo.config.RabbitConfig.EXTERNAL_QUEUE;
import static com.example.rabbitdemo.config.RabbitConfig.QUEUE;

@Log4j2
@Component
public class MessageListener {

    @RabbitListener(queues = {QUEUE, EXTERNAL_QUEUE})
    public void printMessage(final String message){
        log.info("Received message from RabbitMQ: " + message);
    }
}
