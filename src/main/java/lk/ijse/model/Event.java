package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {
    private String EventID;
    private String ReservationID;
    private String EventName;
    private Date EventDate;
    private int NumberOfAttendees;
    private int CustomerPhone;
    private int HallNumber;
}
