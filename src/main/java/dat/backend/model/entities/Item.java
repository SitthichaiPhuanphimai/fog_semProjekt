package dat.backend.model.entities;

public class Item {
    private int order_id;
    private String description;
    private float length;
    private float price;
    private String unit;
    private String type;
    private int quantity;


    public Item(int order_id,String description, int length, String unit, String type, int quantity,float price)
    {
        this.order_id = order_id;
        this.description = description;
        this.length = length;
        this.unit = unit;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    public Item(String description, float lenght, float price, String type) {
        this.description = description;
        this.length = lenght;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }

    public String getType() {
        return type;
    }

    public float getLength() {
        return length;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", length=" + length +
                ", price=" + price +
                ", unit='" + unit + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
