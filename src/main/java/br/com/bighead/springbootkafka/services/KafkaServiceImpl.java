package br.com.bighead.springbootkafka.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaListenerEndpointRegistry registry;



    @Override
    public boolean isStarted(String consumerId) {
        return Objects.requireNonNull(registry.getListenerContainer(consumerId)).isRunning();
    }

    @Override
    public void start(String consumerId) {
        Objects.requireNonNull(registry.getListenerContainer(consumerId)).start();
    }

    @Override
    public void stop(String consumerId) {
        Objects.requireNonNull(registry.getListenerContainer(consumerId)).stop();
    }
}
