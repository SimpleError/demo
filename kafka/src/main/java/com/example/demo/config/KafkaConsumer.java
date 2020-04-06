package com.example.demo.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
       //不指定消费分区
   @KafkaListener(topics = {"kafkaDemo"})
   public void consumer(ConsumerRecord<?, ?> record, Acknowledgment ack){
       System.out.println("partition:"+record.partition()+"offset:"+record.offset()+" msg:"+record.value());
       ack.acknowledge();
   }

    // @Bean("ackContainerFactory")
    // public ConcurrentKafkaListenerContainerFactory ackContainerFactory(ConsumerFactory consumerFactory) {
    //     ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
    //     factory.setConsumerFactory(consumerFactory);
    //     //容器线程数：小于或等于Topic的分区数
    //     factory.setConcurrency(3);
    //     //设置提交偏移量的方式
    //     factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
    //     // 禁止自动启动
    //     factory.setAutoStartup(false);
    //     return factory;
    // }
    //
    // @KafkaListener(containerFactory = "ackContainerFactory",topicPartitions = @TopicPartition(topic = "kafkaDemo",partitions = {"0"}))
    // public void consumer0(ConsumerRecord<?, ?> record, Acknowledgment ack){
    //     System.out.println("partition:"+record.partition()+"offset:"+record.offset()+" msg:"+record.value());
    //     ack.acknowledge();
    // }
    // @KafkaListener(containerFactory = "kafkaListenerContainerFactory",topicPartitions = @TopicPartition(topic = "kafkaDemo",partitions = {"1"}))
    // public void consume1(ConsumerRecord<?, ?> record, Acknowledgment ack){
    //     System.out.println("partition:"+record.partition()+"offset:"+record.offset()+" msg:"+record.value());
    //     ack.acknowledge();
    //
    // }
    // @KafkaListener(containerFactory = "kafkaListenerContainerFactory",topicPartitions = @TopicPartition(topic = "kafkaDemo",partitions = {"2"}))
    // public void consumer2(ConsumerRecord<?, ?> record, Acknowledgment ack){
    //     System.out.println("partition:"+record.partition()+"offset:"+record.offset()+" msg:"+record.value());
    //     ack.acknowledge();
    // }
}
