package dat.backend.model.entities;

import java.util.Objects;

public class Item {
    private String description;
    private float length;
    private float price;
    private String unit;
    private String type;
    private int id;


    public Item(int id, String description, float length, float price, String unit, String type) {
        this.id = id;
        this.description = description;
        this.length = length;
        this.price = price;
        this.unit = unit;
        this.type = type;
    }

    public int getId() {
        return id;
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


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Float.compare(item.length, length) == 0 &&
                Float.compare(item.price, price) == 0 &&
                id == item.id &&
                Objects.equals(description, item.description) &&
                Objects.equals(unit, item.unit) &&
                Objects.equals(type, item.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, length, price, unit, type, id);
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
