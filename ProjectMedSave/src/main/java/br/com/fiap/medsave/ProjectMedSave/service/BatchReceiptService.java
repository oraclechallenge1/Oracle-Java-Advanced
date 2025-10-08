package br.com.fiap.medsave.ProjectMedSave.service;

import br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects.BatchReceiptDTO;

public interface BatchReceiptService {

    Long registerReceipt(BatchReceiptDTO dto);

}
