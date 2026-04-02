package br.com.fiap.medsave.ProjectMedSave.messaging;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockTransferredEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumerB {

    @JmsListener(destination = "stock.transfer.topic", containerFactory = "topicListenerContainerFactory")
    public void onTopicMessageB(StockTransferredEvent event) {
        System.out.println("TOPIC B recebeu: " + event.getMedicineName());
    }
}
