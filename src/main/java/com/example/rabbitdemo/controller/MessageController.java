package com.example.rabbitdemo.controller;

import com.example.rabbitdemo.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    public static final String EXCHANGE = "message-fanout";
    private final RabbitTemplate rabbitTemplate;

    @PostMapping
    public void postMessage(@RequestBody @NotNull final Message message){
        log.info("Received message: " + message);

        //sending to all queues
        rabbitTemplate.convertAndSend(EXCHANGE, "", message.body());
    }
}
