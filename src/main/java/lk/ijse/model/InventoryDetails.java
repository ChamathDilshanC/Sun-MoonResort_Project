package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the inventory details associated with a meal,
 * leveraging Lombok to reduce boilerplate code.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDetails {

    private String inventoryID;
    private int quantity;
    private int minimumStockLevel;
    private String ingredients;
}
