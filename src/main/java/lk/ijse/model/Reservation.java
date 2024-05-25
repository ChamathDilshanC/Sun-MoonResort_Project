package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
    private String ReservationID;
    private Date CheckInDate;
    private Date CheckOutDate;
    private Date ReservationDate;
    private String Duration;
    private int NumberofGuests;
    private String Status;

}
