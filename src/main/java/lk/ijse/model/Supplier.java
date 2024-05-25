package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Supplier {
    private String supplierId;
    private String name;
    private String postalCode;
    private String emailAddress;
    private String contactNumber;

}
