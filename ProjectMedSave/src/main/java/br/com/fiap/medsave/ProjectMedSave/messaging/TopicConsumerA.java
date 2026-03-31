package br.com.fiap.medsave.ProjectMedSave.messaging;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumerA {

    @JmsListener(destination = "demo.topic", containerFactory = "topicListenerContainerFactory")
    public void onTopicMessageA(String body) {
        System.out.println("{TOPIC A} Received message: " + body);
    }

}
