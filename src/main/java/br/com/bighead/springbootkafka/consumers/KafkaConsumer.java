package br.com.bighead.springbootkafka.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(prefix = "kafka", name = "enabled", havingValue = "true")
public class KafkaConsumer {


    @KafkaListener(topics = "test", groupId = "foo", id = "bar")
    public void readMessage(String message) {
        log.atInfo().log("received message: {}", message);
    }
}
