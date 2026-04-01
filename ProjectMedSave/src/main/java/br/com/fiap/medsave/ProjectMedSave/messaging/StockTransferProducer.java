package br.com.fiap.medsave.ProjectMedSave.messaging;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockTransferredEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class StockTransferProducer {

    private final JmsTemplate topicJmsTemplate;
    private final JmsTemplate queueJmsTemplate;

    public StockTransferProducer(
            @Qualifier("queueJmsTemplate") JmsTemplate topicJmsTemplate,
            @Qualifier("topicJmsTemplate") JmsTemplate queueJmsTemplate) {
        this.topicJmsTemplate = topicJmsTemplate;
        this.queueJmsTemplate = queueJmsTemplate;
    }

    public void sendToQueue(StockTransferredEvent event) {
        queueJmsTemplate.convertAndSend("stock.transfer.queue", event);
    }

    public void sendToTopic(StockTransferredEvent event) {
        queueJmsTemplate.convertAndSend("stock.transfer.topic", event);
    }

}
