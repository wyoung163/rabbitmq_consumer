package com.rabbitmq.consumer;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.DataInput;
import java.io.IOException;

@Component
public class SimpleChannelMessageListener implements ChannelAwareMessageListener {
    private final CafeService cafeService;

    public SimpleChannelMessageListener(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @Override
    public void onMessage(Message message, Channel channel) throws IOException{
        System.out.println(Thread.currentThread().getName() + ": onMessage");

        //ObjectMapper objectMapper = new ObjectMapper();
        Message m = MessageBuilder
                .withBody(message.getBody())
                .build();
        cafeService.accept(m);
    }
}
