import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Checkout;
import Model.Item;
import Model.PricingRules;

class CheckoutTest {

    private PricingRules pricingRules;
    private Checkout checkout;

    @BeforeEach
    void setUp() throws Exception {
        pricingRules = new PricingRules("../../data/pricing_rules.json");
        checkout = new Checkout(pricingRules);
    }

    @Test
    void testScan() {
        // Scan some items
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("C");
        checkout.scan("A");
        checkout.scan("D");
        checkout.scan("B");

        // Check that items were added correctly
        assertEquals(2, pricingRules.getItem("A").getQuantity());
        assertEquals(2, pricingRules.getItem("B").getQuantity());
        assertEquals(1, pricingRules.getItem("C").getQuantity());
        assertEquals(1, pricingRules.getItem("D").getQuantity());
    }

    @Test
    void testGetTotal() {
        // Scan some items
        checkout.scan("A");
        checkout.scan("B");
        checkout.scan("C");
        checkout.scan("A");
        checkout.scan("D");
        checkout.scan("B");

        // Check the total price
        assertEquals(180, checkout.getTotal());
    }

    @Test
    void testGetPrice() {
        // Test the price calculation for item A
        Item itemA = new Item("A", 50, 130, 3);
        itemA.addQuantity();
        assertEquals(50, itemA.getPrice());
        itemA.addQuantity();
        assertEquals(100, itemA.getPrice());
        itemA.addQuantity();
        assertEquals(130, itemA.getPrice());
        itemA.addQuantity();
        assertEquals(180, itemA.getPrice());
        itemA.addQuantity();
        assertEquals(230, itemA.getPrice());
    }

}
