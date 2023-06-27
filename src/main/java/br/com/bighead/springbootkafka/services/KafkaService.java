package br.com.bighead.springbootkafka.services;


public interface KafkaService {

    boolean isStarted(String consumerId);
    void start(String consumerId);

    void stop (String consumerId);
}
