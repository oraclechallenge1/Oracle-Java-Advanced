```mermaid

---
config:
  theme: neutral
---
classDiagram
direction LR
    class Medicine {
	    -Long medicineId
	    -String comercialName
	    -String activeIngredient
	    -String pharmaceuticalForm
	    -String unitMeasure
	    -String manufacturer
	    -String anvisaRecord
	    -String categoryMedicine
	    -Integer minimalStock
	    -String status
    }
    class StockBatch {
	    -Long batchId
	    -String numberBatch
	    -LocalDate dateEntry
	    -LocalDate dateValidity
	    -Long currentQuantity
	    -BigDecimal unitaryCost
	    -String locationMedicine
	    -String supplier
	    -String status
	    -Long medicineId
    }
    class Dispensation {
	    -Long dispensationId
	    -LocalDate dateDispensation
	    -Integer withdrawalAmount
	    -String sectorDestiny
	    -String movementType
	    -String observation
	    -Long userId
	    -Long batchId
    }
    class UserSys {
	    -Long userId
	    -String nameUser
	    -String positionUser
	    -String login
	    -String passwordUser
	    -String profileUser
	    -String email
	    -String phoneNumber
    }

	<<Class>> Medicine
	<<Class>> StockBatch
	<<Class>> Dispensation
	<<Class>> UserSys

    Medicine "1" --|> "n" StockBatch
    StockBatch "1" --|> "n" Dispensation
    UserSys "1" --|> "n" Dispensation

