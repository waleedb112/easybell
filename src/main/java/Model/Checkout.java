package Model;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private final PricingRules pricingRules;
    private final List<Item> items;

    public Checkout(PricingRules pricingRules) {
        this.pricingRules = pricingRules;
        this.items = new ArrayList<>();
    }

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

    public int getTotal() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }
}
