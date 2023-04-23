package Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class PricingRules {
    private final Map<String, Item> items;

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

    public Item getItem(String name) {
        return items.get(name);
    }
}
