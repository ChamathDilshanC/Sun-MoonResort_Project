package lk.ijse.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Payment {
    private String paymentId;
    private String reservationId;
    private double amount;
    private LocalDate date;
    private String method;
    private String status;
}
