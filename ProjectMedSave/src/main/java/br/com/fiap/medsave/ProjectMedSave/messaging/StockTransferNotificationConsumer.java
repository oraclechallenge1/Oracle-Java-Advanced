package br.com.fiap.medsave.ProjectMedSave.messaging;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockTransferredEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class StockTransferNotificationConsumer {

    @JmsListener(destination = "stock.transfer.topic", containerFactory = "topicListenerContainerFactory")
    public void handleStockTransferNotification(StockTransferredEvent event) {
        System.out.println("Notificando transferência de: " + event.getMedicineName());
    }

}
