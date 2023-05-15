package dat.backend.model.entities;

import java.sql.Timestamp;

public class Order
{
    private int id;
    private String username;
    private String status;
    private Timestamp timestamp;

    public Order(int id, String username, String status)

    {
        this.id = id;
        this.username = username;
        this.status = status;
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

    @Override
    public String toString()
    {
        return "id:" + id +" " + "username: " + username + " "+ "status: "+ status;
    }
}
