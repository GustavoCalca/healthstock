package model;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//Model class for a Product.
public class Medicamento {
    
    private final StringProperty medicamentoCommercialName;
    private final StringProperty medicamentoRealName;
    private final StringProperty medicamentoLot;
    private final StringProperty medicamentoSector;
    private final StringProperty medicamentoType;
    
    private final IntegerProperty medicamentoQuantity;
    
    private final ObjectProperty<LocalDate> medicamentoVality;
    
    //Constructor of the Class
    //Works similarly to inserting values on a DataBase Table.
    public Medicamento(
            String medicamentoCommercialName,
            String medicamentoRealName,
            String medicamentoLot,
            String medicamentoSector,
            String medicamentoType,
            Integer medicamentoQuantity,
            LocalDate medicamentoVality
    ){
        this.medicamentoCommercialName
                = new SimpleStringProperty(medicamentoCommercialName);
        this.medicamentoRealName
                = new SimpleStringProperty(medicamentoRealName);
        this.medicamentoLot
                = new SimpleStringProperty(medicamentoLot);
        this.medicamentoSector
                = new SimpleStringProperty(medicamentoSector);
        this.medicamentoType
                = new SimpleStringProperty(medicamentoType);
        this.medicamentoQuantity
                = new SimpleIntegerProperty(medicamentoQuantity);
        this.medicamentoVality
                = new SimpleObjectProperty<>(medicamentoVality);
    }

    //medicamentoCommercialName
    public String getMedicamentoCommercialName() {
        return medicamentoCommercialName.get();
    }

    public void setMedicamentoCommercialName(String value) {
        medicamentoCommercialName.set(value);
    }

    public StringProperty medicamentoCommercialNameProperty() {
        return medicamentoCommercialName;
    }

    //medicamentoRealName
    public String getMedicamentoRealName() {
        return medicamentoRealName.get();
    }

    public void setMedicamentoRealName(String value) {
        medicamentoRealName.set(value);
    }

    public StringProperty medicamentoRealNameProperty() {
        return medicamentoRealName;
    }
    
    //medicamentoLot
    public String getMedicamentoLot() {
        return medicamentoLot.get();
    }

    public void setMedicamentoLot(String value) {
        medicamentoLot.set(value);
    }

    public StringProperty medicamentoLotProperty() {
        return medicamentoLot;
    }

    //medicamentoSector
    public String getMedicamentoSector() {
        return medicamentoSector.get();
    }

    public void setMedicamentoSector(String value) {
        medicamentoSector.set(value);
    }

    public StringProperty medicamentoSectorProperty() {
        return medicamentoSector;
    }

    //medicamentoType
    public String getMedicamentoType() {
        return medicamentoType.get();
    }

    public void setMedicamentoType(String value) {
        medicamentoType.set(value);
    }

    public StringProperty medicamentoTypeProperty() {
        return medicamentoType;
    }

    //medicamentoQuantity
    public int getMedicamentoQuantity() {
        return medicamentoQuantity.get();
    }

    public void setMedicamentoQuantity(int value) {
        medicamentoQuantity.set(value);
    }

    public IntegerProperty medicamentoQuantityProperty() {
        return medicamentoQuantity;
    }

    //medicamentoVality
    public LocalDate getMedicamentoVality() {
        return medicamentoVality.get();
    }

    public void setMedicamentoVality(LocalDate value) {
        medicamentoVality.set(value);
    }

    public ObjectProperty<LocalDate> medicamentoValityProperty() {
        return medicamentoVality;
    }
    
    
}
