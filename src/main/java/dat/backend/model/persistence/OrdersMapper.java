package dat.backend.model.persistence;

import dat.backend.model.entities.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrdersMapper
{


   public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool)
    {
        String sql = "SELECT * FROM orders";
        ArrayList<Order>ordersList = new ArrayList<>();


        try(Connection conn = connectionPool.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql))

        {
            while (rs.next())
            {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String status = rs.getString("Status");

                Order order = new Order(id,username,status);
                ordersList.add(order);
            }


        } catch (SQLException s)
        {
            System.out.println("Error in the database");
        }

        return ordersList;
    }



}
