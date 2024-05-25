package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeService {
    private String employeeID;
    private String serviceID;
    private String serviceDate;
    private double servicePrice;
    private double totalServicePrice;
    private int hoursAllocated;

}
