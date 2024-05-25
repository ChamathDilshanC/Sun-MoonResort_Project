package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Inventory {
    private String inventoryID;
    private String mealID;
    private String Name;
    private int Quantity;
    private int MinimumStockLevel;
    private String Ingredients;

}
