package com.rabbitmq.consumer;

import org.springframework.amqp.core.Message;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

//@EnableAsync
@Service
public class CafeService {

    //@Async
    void accept(Message m){
        System.out.println(Thread.currentThread().getName() + ": accept");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
