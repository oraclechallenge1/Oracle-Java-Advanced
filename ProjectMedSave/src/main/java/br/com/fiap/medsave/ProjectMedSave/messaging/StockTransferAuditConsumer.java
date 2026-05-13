package br.com.fiap.medsave.ProjectMedSave.messaging;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.StockTransferredEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class StockTransferAuditConsumer {

    @JmsListener(destination = "stock.transfer.topic", containerFactory = "topicListenerContainerFactory")
    public void registerTransferAudit(StockTransferredEvent event) {
        System.out.println("Auditando transferência: " + event.getMedicineName());
    }
}
