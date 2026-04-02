package br.com.fiap.medsave.ProjectMedSave.messaging;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockTransferredEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @JmsListener(destination = "stock.transfer.queue", containerFactory = "jmsListenerContainerFactory")
    public void onQueueMessage(StockTransferredEvent event) {
        System.out.println("QUEUE recebeu: " + event.getMedicineName());
    }
}
