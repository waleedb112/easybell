package Model;

public class Item {
    private final String name;
    private final int unitPrice;
    private final int specialPrice;
    private final int specialQuantity;
    private int quantity;

    public Item(String name, int unitPrice, int specialPrice, int specialQuantity) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.specialPrice = specialPrice;
        this.specialQuantity = specialQuantity;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

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

    public void addQuantity() {
        this.quantity += 1;
    }

    public int getQuantity() {
        return quantity;
    }
}
