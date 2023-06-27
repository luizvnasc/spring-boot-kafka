package br.com.bighead.springbootkafka.resources;

import br.com.bighead.springbootkafka.services.ProducerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class ProducerResource {

    private static final String PATH = "/produce";

    private final ProducerService producerService;


    @PostMapping(PATH)
    public void produce(@RequestBody String msg){
        producerService.sendMessage(msg);
    }

}
