package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Checkout class represents a checkout process for a shopping cart of items with pricing rules. It maintains
 * a list of items that have been scanned, and provides a method for getting the total cost of all items in the cart.
 */
public class Checkout {
    private final PricingRules pricingRules; // The pricing rules for items in the cart
    private final List<Item> items; // The list of items in the cart

    /**
     * Constructs a Checkout object with the given pricing rules for items.
     *
     * @param pricingRules the pricing rules for items in the cart
     */
    public Checkout(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
        this.items = new ArrayList<>();
    }

    /**
     * Scans an item with the given name and adds it to the cart. If an item with the given name is not found in
     * the pricing rules, an exception is thrown.
     *
     * @param name the name of the item to scan
     * @throws Exception if an item with the given name is not found in the pricing rules
     */
    public void scan(String name) throws Exception {
        Item item = pricingRules.getItem(name);

        if (item != null) {
            item.addQuantity();
            if (!items.contains(item)) {
                items.add(item);
            }
        } else {
            throw new Exception("Object " + name + " not found");
        }
    }

    /**
     * Calculates and returns the total cost of all items in the cart based on their current quantities and pricing
     * rules.
     *
     * @return the total cost of all items in the cart based on their current quantities and pricing rules
     */
    public int getTotal() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }
}
