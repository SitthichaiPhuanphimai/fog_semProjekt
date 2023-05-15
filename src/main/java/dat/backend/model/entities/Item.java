package dat.backend.model.entities;

public class Item {
    private String description;
    private float length;
    private float price;
    private String unit;
    private String type;


    public Item(String description, int length, float price, String unit, String type) {
        this.description = description;
        this.length = length;
        this.price = price;
        this.unit = unit;
        this.type = type;
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
