package dat.backend.model.entities;

public class Material {
    private int id;
    private String description;
    private float price;
    private int unitId;
    private int materialType;
    private int materialLength;


    public Material(int id, String description, float price, int unitId, int materialType, int materialLength) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.unitId = unitId;
        this.materialType = materialType;
        this.materialLength = materialLength;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getUnitId() {
        return unitId;
    }

    public int getMaterialType() {
        return materialType;
    }

    public int getMaterialLength() {
        return materialLength;
    }


    public void setPrice(float price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", unitId=" + unitId +
                ", materialType=" + materialType +
                ", materialLength=" + materialLength +
                '}';
    }
}
