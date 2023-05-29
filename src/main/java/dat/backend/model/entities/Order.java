package dat.backend.model.entities;


public class Order
{
    private int id;
    private String username;
    private String status;
    private float totalPrice;
    private String phoneNumber;

    public Order(int id, String username,String phoneNumber, String status, float totalPrice)
    {
        this.id = id;
        this.username = username;
        this.status = status;
        this.totalPrice = totalPrice;
        this.phoneNumber = phoneNumber;
    }


    public int getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getStatus()
    {
        return status;
    }

    public float getTotalPrice()
    {
        return totalPrice;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    @Override
    public String toString()
    {
        return "id:" + id + " " + "username: " + username + " " + "status: " + status;
    }
}
