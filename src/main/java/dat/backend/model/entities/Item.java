package dat.backend.model.entities;

public class Item {
    private String description;
    private int lenght;
    private float price;
    private String unit;
    private String type;


    public Item(String description, int lenght, float price, String unit, String type) {
        this.description = description;
        this.lenght = lenght;
        this.price = price;
        this.unit = unit;
        this.type = type;
    }



}
