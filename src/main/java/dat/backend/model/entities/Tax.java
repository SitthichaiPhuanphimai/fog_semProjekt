package dat.backend.model.entities;

public class Tax {
    private int id;
    private double value;
    private String name;

    public Tax(int id, double value, String name) {
        this.id = id;
        this.value = value;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }
}
