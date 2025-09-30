package br.com.fiap.medsave.ProjectMedSave.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MEDICINES")
public class Medicine {

    // Chave Primária
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assumindo que a PK é autoincrementável
    @Column(name = "MEDICINE_ID")
    private @Getter @Setter Long medicineId; // Numeric

    // Atributos da Entidade
    @Column(name = "COMERCIAL_NAME", length = 255)
    private @Getter @Setter String comercialName; // Varchar(255)

    @Column(name = "ACTIVE_INGREDIENT", length = 200)
    private @Getter @Setter String activeIngredient; // Varchar(200)

    @Column(name = "PHARMACEUTICAL_FORM", length = 100)
    private @Getter @Setter String pharmaceuticalForm; // Varchar(100)

    @Column(name = "UNIT_MEASURE", length = 20)
    private @Getter @Setter String unitMeasure; // Varchar(20)

    @Column(name = "MANUFACTURER", length = 150)
    private @Getter @Setter String manufacturer; // Varchar(150)

    @Column(name = "ANVISA_RECORD", length = 255)
    private @Getter @Setter String anvisaRecord; // Varchar(255)

    @Column(name = "CATEGORY_MEDICINE", length = 100)
    private @Getter @Setter String categoryMedicine; // Varchar(100)

    @Column(name = "MINIMAL_STOCK")
    private @Getter @Setter Integer minimalStock; // Numeric(4)

    @Column(name = "STATUS", length = 20)
    private @Getter @Setter String status; // Varchar(20)

    // Relacionamento (Um Medicamento pode estar em Múltiplos Lotes)
    // Mapeamento One-to-Many com STOCK_BATCH
    // Mapeado pelo campo 'medicine' na classe StockBatch
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StockBatch> stockBatches;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(medicineId, medicine.medicineId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(medicineId);
    }

    @Override
    public String toString() {
        return "=== Medicine ===" +
                "medicineId=" + medicineId +
                ", comercialName='" + comercialName + '\'' +
                ", activeIngredient='" + activeIngredient + '\'' +
                ", pharmaceuticalForm='" + pharmaceuticalForm + '\'' +
                ", unitMeasure='" + unitMeasure + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", anvisaRecord='" + anvisaRecord + '\'' +
                ", categoryMedicine='" + categoryMedicine + '\'' +
                ", minimalStock=" + minimalStock +
                ", status='" + status + '\'' +
                ", stockBatches=" + stockBatches +
                '}';
    }
}
