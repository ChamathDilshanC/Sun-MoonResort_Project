package lk.ijse.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class Employee extends Supplier {
    private String employeeID;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    private int phone;
    private LocalDate hireDate;
    private double salary;
    private String department;

    public Employee(String employeeID, String firstName, String lastName, String text, String txtEmailText, int txtPhoneText, LocalDate value, double salary, String cmbDepartmentValue) {
            this.employeeID = employeeID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.position = text;
            this.email = txtEmailText;
            this.phone = txtPhoneText;
            this.hireDate = value;
            this.salary = salary;
            this.department = cmbDepartmentValue;

    }

    public Employee(String employeeID, String firstName, String lastName, int phone) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public Employee(String text, String text1, String text2, String text3, String text4, String text5, LocalDate value, double salary, String value1) {
        this.employeeID = text;
        this.firstName = text1;
        this.lastName = text2;
        this.position = text3;
        this.email = text4;
        this.phone = Integer.parseInt(text5);
        this.hireDate = value;
        this.salary = salary;
        this.department = value1;
    }
}
