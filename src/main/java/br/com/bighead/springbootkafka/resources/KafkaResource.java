package br.com.bighead.springbootkafka.resources;


import br.com.bighead.springbootkafka.services.KafkaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class KafkaResource {

    private static final String PATH = "/consumer";
    private static final String STOP_PATH = PATH + "/{id}/stop";
    private static final String START_PATH = PATH + "/{id}/start";

    private final KafkaService kafkaService;

    @GetMapping(PATH)
    public boolean isStarted(@RequestParam("id") String id){
        return kafkaService.isStarted(id);
    }

    @GetMapping(STOP_PATH)
    public ResponseEntity<String> stop(@PathVariable("id") String id){
        kafkaService.stop(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(START_PATH)
    public ResponseEntity<String> start(@PathVariable("id") String id){
        kafkaService.start(id);
        return ResponseEntity.ok().build();
    }
}
