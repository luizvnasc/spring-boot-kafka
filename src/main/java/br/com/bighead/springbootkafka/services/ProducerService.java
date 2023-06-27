package br.com.bighead.springbootkafka.services;

public interface ProducerService {

    void sendMessage(String msg);
}
