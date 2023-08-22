package com.rabbitmq.consumer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    private static final String EXCHANGE = "forest.exchange";
    private static final String QUEUE = "forest.queue";
    private static final String ROUTING_KEY = "forest.key.#";
    private final SimpleChannelMessageListener setMessageListener;

    public Config(SimpleChannelMessageListener setMessageListener) {
        this.setMessageListener = setMessageListener;
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean("simpleMessageListenerContainer")
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(QUEUE);
        //simpleMessageListenerContainer.setConcurrency("10");
        simpleMessageListenerContainer.setMessageListener(setMessageListener);
        return simpleMessageListenerContainer;
    }
}
