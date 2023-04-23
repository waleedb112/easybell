import Model.Checkout;
import Model.PricingRules;

import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {

        // Create pricing rules from JSON file
        PricingRules pricingRules = new PricingRules("../../../data/pricing_rules.json");

        // Create checkout object
        Checkout checkout = new Checkout(pricingRules);

        // Scan items
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("C");
        checkout.scan("A");
        checkout.scan("D");
        checkout.scan("B");


        // Print total price
        int totalPrice = checkout.getTotal();
        System.out.println("Total price: " + totalPrice);
    }
}
