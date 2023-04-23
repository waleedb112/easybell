package Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the pricing rules for items in a store.
 */
public class PricingRules {

    /**
     * A map of items and their associated pricing information.
     */
    private final Map<String, Item> items;

    /**
     * Constructs a new PricingRules object by parsing a JSON file containing the pricing information.
     *
     * @param filename The name of the JSON file to parse.
     * @throws Exception If an error occurs while parsing the JSON file.
     */
    public PricingRules(String filename) throws Exception {
        var parser = new JSONParser();
        try (var reader = new FileReader(filename)) {
            JSONObject json = (JSONObject) parser.parse(reader);
            items = new HashMap<>();

            for (var element : json.keySet()) {
                String name = (String) element;
                JSONObject itemJson = (JSONObject) json.get(name);
                int unitPrice = ((Long) itemJson.get("unit_price")).intValue();
                int specialPrice = ((Long) itemJson.get("special_price")).intValue();
                int specialQuantity = ((Long) itemJson.get("special_quantity")).intValue();
                items.put(name, new Item(name, unitPrice, specialPrice, specialQuantity));
            }
        }
    }

    /**
     * Returns the Item object associated with the given item name.
     *
     * @param name The name of the item to retrieve.
     * @return The Item object associated with the given item name.
     */
    public Item getItem(String name) {
        return items.get(name);
    }
}
