package lk.ijse.model.tm;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Data;

@Data

public class ReservationCartItem {
    private final SimpleStringProperty reservationId;
    private final SimpleStringProperty customerId;
    private final SimpleStringProperty packageId;
    private final SimpleDoubleProperty packagePrice;
    private final SimpleStringProperty serviceId;
    private final SimpleDoubleProperty servicePrice;
    private final SimpleStringProperty RoomID;
    private final SimpleDoubleProperty totalPrice;

    public ReservationCartItem(String reservationId, String customerId, String packageId,
                               double packagePrice, String serviceId, double servicePrice, Object totalPrice, String roomID) {
        this.reservationId = new SimpleStringProperty(reservationId);
        this.customerId = new SimpleStringProperty(customerId);
        this.packageId = new SimpleStringProperty(packageId);
        this.packagePrice = new SimpleDoubleProperty(packagePrice);
        this.serviceId = new SimpleStringProperty(serviceId);
        this.servicePrice = new SimpleDoubleProperty(servicePrice);
        this.RoomID = new SimpleStringProperty(String.valueOf(roomID));
        this.totalPrice = new SimpleDoubleProperty((Double) totalPrice);


    }


    // Getters and setters
    public String getReservationId() { return reservationId.get(); }
    public String getCustomerId() { return customerId.get(); }
    public String getPackageId() { return packageId.get(); }
    public double getPackagePrice() { return packagePrice.get(); }
    public String getServiceId() { return serviceId.get(); }
    public double getServicePrice() { return servicePrice.get(); }
    public String getRoomID() { return RoomID.get(); }
    public double getTotalPrice() { return totalPrice.get(); }
}
