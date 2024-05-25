package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Room {
    private  String RoomID;
    private String Type;
    private double PricePerNight;
    private String Status;


}
