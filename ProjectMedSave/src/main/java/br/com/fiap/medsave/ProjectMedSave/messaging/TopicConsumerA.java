package br.com.fiap.medsave.ProjectMedSave.messaging;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockTransferredEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumerA {

    @JmsListener(destination = "stock.transfer.topic", containerFactory = "topicListenerContainerFactory")
    public void onTopicMessageA(StockTransferredEvent event) {
        System.out.println("TOPIC A recebeu: " + event.getMedicineName());
    }

}
