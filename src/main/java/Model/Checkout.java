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

    public void scan(String name) {
        Item item = pricingRules.getItem(name);

        if (item != null) {
            item.addQuantity();
            if (!items.contains(item)) {
                items.add(item);
            }
        }
    }

    public int getTotal() {
        return items.stream().mapToInt(Item::getPrice).sum();
    }
}
