package br.com.bighead.springbootkafka.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private static final String topic = "test";
    @Override
    public void sendMessage(String msg) {
        log.atInfo().log(" Sending message '{}' to topic '{}'",msg,topic);
        kafkaTemplate.send(topic,msg);
    }
}
