package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer extends Package {
    private String CustomerID;
    private String FirstName;
    private String LastName;
    private String Nationality;
    private String Email;
    private String Phone;
    private String Address;
    private int TotalReservations;
    private Date RegistrationDate;
}
