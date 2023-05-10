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

    public Item(String description, float lenght, float price) {
        this.description = description;
        this.length = lenght;
        this.price = price;
    }



    public float getLength() {
        return length;
    }
}
