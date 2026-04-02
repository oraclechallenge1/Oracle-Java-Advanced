package br.com.fiap.medsave.ProjectMedSave.messaging;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockTransferredEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class StockTransferProducer {

    private final JmsTemplate queueJmsTemplate;
    private final JmsTemplate topicJmsTemplate;

    public StockTransferProducer(
            @Qualifier("queueJmsTemplate") JmsTemplate queueJmsTemplate,
            @Qualifier("topicJmsTemplate") JmsTemplate topicJmsTemplate) {
        this.queueJmsTemplate = queueJmsTemplate;
        this.topicJmsTemplate = topicJmsTemplate;
    }

    public void sendToQueue(StockTransferredEvent event) {
        queueJmsTemplate.convertAndSend("stock.transfer.queue", event);
    }

    public void sendToTopic(StockTransferredEvent event) {
        topicJmsTemplate.convertAndSend("stock.transfer.topic", event);
    }
}