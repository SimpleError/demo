package com.example.demo.config;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.UUID;

@Component
@EnableScheduling
public class KafkaProducer {

    @Resource
    private KafkaTemplate kafkaTemplate;

    @Scheduled(fixedRate = 1000)
    public void send(){
        for(int i = 0; i < 10; i++){
            final int partition = i%3;
            String message = UUID.randomUUID().toString();
            ListenableFuture future0 = kafkaTemplate.send("kafkaDemo",partition,"",message);
            future0.addCallback(o -> System.out.println("success\tpartition-"+ partition +"：" + message),
                    throwable -> System.out.println("error\tpartition-"+ partition +"：" + message));
        }
        // ListenableFuture future1 = kafkaTemplate.send("kafkaDemo",1,"",message);
        // future1.addCallback(o -> System.out.println("send1-消息发送成功：" + message),
        //         throwable -> System.out.println("send1-消息发送失败：" + message));
        // ListenableFuture future2 = kafkaTemplate.send("kafkaDemo",2,"",message);
        // future2.addCallback(o -> System.out.println("send2-消息发送成功：" + message),
        //         throwable -> System.out.println("send2-消息发送失败：" + message));
    }
}
