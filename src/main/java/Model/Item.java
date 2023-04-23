package Model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Item class represents a product item that can be added to a shopping cart.
 * It contains information about the item's name, regular price per unit, special price per unit (if a certain
 * quantity is purchased), special quantity at which the special price applies, and the quantity of the item
 * currently in the cart.
 */
@Getter
@Setter
public class Item {
    private final String name; // The name of the item
    private final int unitPrice; // The regular price per unit of the item
    private final int specialPrice; // The special price per unit of the item (if a certain quantity is purchased)
    private final int specialQuantity; // The quantity at which the special price applies
    private int quantity; // The current quantity of the item in the cart

    /**
     * Constructs an Item object with the given name, regular price per unit, special price per unit (if a certain
     * quantity is purchased), and special quantity at which the special price applies.
     *
     * @param name the name of the item
     * @param unitPrice the regular price per unit of the item
     * @param specialPrice the special price per unit of the item (if a certain quantity is purchased)
     * @param specialQuantity the quantity at which the special price applies
     */
    public Item(String name, int unitPrice, int specialPrice, int specialQuantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.specialPrice = specialPrice;
        this.specialQuantity = specialQuantity;
        this.quantity = 0;
    }

    /**
     * Calculates and returns the total price of the item based on the current quantity and pricing rules.
     * If the special quantity is 0 or the current quantity is less than the special quantity, the total price is
     * simply the unit price times the quantity. Otherwise, the total price is calculated based on the number of
     * "specials" (i.e. the number of times the special quantity has been purchased) and the remainder quantity that
     * doesn't make up a full special.
     *
     * @return the total price of the item based on the current quantity and pricing rules
     */
    public int getPrice() {
        int price;
        if (specialQuantity == 0 || getQuantity() < specialQuantity) {
            price = unitPrice * quantity;
        } else {
            int specials = getQuantity() / specialQuantity;
            int remainder = getQuantity() % specialQuantity;
            price = specials * specialPrice + remainder * unitPrice;
        }
        return price;
    }

    /**
     * Adds one to the current quantity of the item in the cart.
     */
    public void addQuantity() {
        this.quantity += 1;
    }
}
