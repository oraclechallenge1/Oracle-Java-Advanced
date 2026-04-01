package br.com.fiap.medsave.ProjectMedSave.presentation.transferObjects;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StockTransferredEvent implements Serializable {

    private Long medicineId;
    private String medicineName;
    private Long batchId;
    private String batchNumber;
    private Long sourceLocationId;
    private String sourceLocationName;
    private Long destinationLocationId;
    private String destinationLocationName;
    private Integer quantity;
    private LocalDateTime transferredAt;

    public StockTransferredEvent() {
    }

    public StockTransferredEvent(Long medicineId, String medicineName, Long batchId, String batchNumber,
                                 Long sourceLocationId, String sourceLocationName,
                                 Long destinationLocationId, String destinationLocationName,
                                 Integer quantity, LocalDateTime transferredAt) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.batchId = batchId;
        this.batchNumber = batchNumber;
        this.sourceLocationId = sourceLocationId;
        this.sourceLocationName = sourceLocationName;
        this.destinationLocationId = destinationLocationId;
        this.destinationLocationName = destinationLocationName;
        this.quantity = quantity;
        this.transferredAt = transferredAt;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Long getSourceLocationId() {
        return sourceLocationId;
    }

    public void setSourceLocationId(Long sourceLocationId) {
        this.sourceLocationId = sourceLocationId;
    }

    public String getSourceLocationName() {
        return sourceLocationName;
    }

    public void setSourceLocationName(String sourceLocationName) {
        this.sourceLocationName = sourceLocationName;
    }

    public Long getDestinationLocationId() {
        return destinationLocationId;
    }

    public void setDestinationLocationId(Long destinationLocationId) {
        this.destinationLocationId = destinationLocationId;
    }

    public String getDestinationLocationName() {
        return destinationLocationName;
    }

    public void setDestinationLocationName(String destinationLocationName) {
        this.destinationLocationName = destinationLocationName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getTransferredAt() {
        return transferredAt;
    }

    public void setTransferredAt(LocalDateTime transferredAt) {
        this.transferredAt = transferredAt;
    }

}
