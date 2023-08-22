package com.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *  queue에 있는 정보를 받아주는 Listener
 *  AMQP에서 제공하는 @RabbitListener를 이용해서 쉽게 정보 받아올 수 있음
 */
@Component
public class Listener {
    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    @RabbitListener(queues = "forest.queue")
    public void receiveMessage(final Message message){
        log.info(message.toString());
    }
}
