package br.com.fiap.medsave.ProjectMedSave.messaging;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping("/api")
public class MessageProducer implements CommandLineRunner {

    private final JmsTemplate queueJmsTemplate;
    private final JmsTemplate topicJmsTemplate;

    public MessageProducer(
            @Qualifier("queueJmsTemplate") JmsTemplate queueJmsTemplate,
            @Qualifier("topicJmsTemplate") JmsTemplate topicJmsTemplate) {
        this.queueJmsTemplate = queueJmsTemplate;
        this.topicJmsTemplate = topicJmsTemplate;
    }

    @GetMapping
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/send/queue/{n}")
    public ResponseEntity<String> sendQueue(@PathVariable int n) {
        for (int i = 1; i <= n; i++) {
            this.queueJmsTemplate.convertAndSend("demo.queue", "Message " + i);
        }
        return ResponseEntity.ok("Sent " + n + " messages to queue");
    }

    @PostMapping("/send/topic/{n}")
    public ResponseEntity<String> sendTopic(@PathVariable int n) {
        for (int i = 1; i <= n; i++) {
            this.topicJmsTemplate.convertAndSend("demo.topic", "Message Topic " + i);
        }
        return ResponseEntity.ok("Sent " + n + " messages to topic");
    }

    @Override
    public void run(String... args) throws Exception {
        this.queueJmsTemplate.convertAndSend("demo.queue", "Hello Queue!");
        this.topicJmsTemplate.convertAndSend("demo.topic", "Hello Topic!");
    }
}
