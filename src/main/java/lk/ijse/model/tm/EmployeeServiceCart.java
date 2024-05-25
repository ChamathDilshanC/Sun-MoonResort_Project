package lk.ijse.model.tm;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmployeeServiceCart {
    private SimpleStringProperty employeeID;
    private SimpleStringProperty serviceID;
    private SimpleStringProperty serviceDate;
    private SimpleDoubleProperty servicePrice;
    private SimpleDoubleProperty totalServicePrice;
    private SimpleIntegerProperty hoursAllocated;

    public EmployeeServiceCart(String employeeID, String serviceID, String string, double servicePrice, double totalServicePrice, int hoursAllocated) {
        this.employeeID = new SimpleStringProperty(employeeID);
        this.serviceID = new SimpleStringProperty(serviceID);
        this.serviceDate = new SimpleStringProperty(string);
        this.servicePrice = new SimpleDoubleProperty(servicePrice);
        this.totalServicePrice = new SimpleDoubleProperty(totalServicePrice);
        this.hoursAllocated = new SimpleIntegerProperty(hoursAllocated);

    }
}


