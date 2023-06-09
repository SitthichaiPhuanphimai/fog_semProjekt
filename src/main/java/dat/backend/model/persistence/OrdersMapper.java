package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersMapper
{


    public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool)
    {
        String sql = "SELECT * FROM orders";
        ArrayList<Order> ordersList = new ArrayList<>();


        try (Connection conn = connectionPool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql))
        {
            while (rs.next())
            {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String phoneNumber = rs.getString("phone_number");
                String status = rs.getString("status");
                float totalPrice = rs.getFloat("totalPrice");

                Order order = new Order(id, username,phoneNumber, status, totalPrice);
                ordersList.add(order);
            }


        } catch (SQLException s)
        {
            System.out.println("Error in the database");
        }

        return ordersList;
    }


    public static void deleteOrder(int id, ConnectionPool connectionPool)
    {
        try (Connection conn = connectionPool.getConnection())
        {

            String sqlDeleteMaterials = "DELETE FROM material_list WHERE order_id = ?";
            String sqlDeleteOrder = "DELETE FROM orders WHERE id = ?";

            try (PreparedStatement statement = conn.prepareStatement(sqlDeleteMaterials))
            {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

            try (PreparedStatement statement = conn.prepareStatement(sqlDeleteOrder))
            {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public static ArrayList<Item> getItemList(int id, ConnectionPool connectionPool) throws SQLException
    {
        ArrayList<Item> itemsList = new ArrayList<>();

        String sql = "SELECT material_list.order_id, material.description, unit.unit, " +
                "material_type.type, material_length.length, material_list.quantity, material.price_per_unit " +
                "FROM material_list " +
                "JOIN material ON material_list.material_id = material.id " +
                "JOIN unit ON material.unit_id = unit.id " +
                "JOIN material_type ON material.material_type_id = material_type.id " +
                "JOIN material_length ON material.material_length_id = material_length.id " +
                "WHERE material_list.order_id = ?";

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql))
        {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery())
            {
                while (rs.next())
                {
                    int orderID = rs.getInt("order_id");
                    String description = rs.getString("description");
                    int length = rs.getInt("length");
                    String unit = rs.getString("unit");
                    String type = rs.getString("type");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price_per_unit");

                    Item item = new Item(orderID, description, length, unit, type, quantity, price);
                    itemsList.add(item);
                }
            }

        } catch (SQLException e)
        {
            System.out.println("Error in the database");
        }

        return itemsList;
    }

    public static void updateOrderStatus(String orderID, String status, ConnectionPool connectionPool) throws DatabaseException
    {
        try (Connection conn = connectionPool.getConnection())
        {
            String sql = "UPDATE orders SET status = ? WHERE id = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql))
            {
                statement.setString(1, status);
                statement.setString(2, orderID);
                statement.executeUpdate();

            } catch (SQLException e)
            {
                System.out.println("Error in the database");
            }


        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public static Order createOrder(String username, String phoneNumber, float totalPrice, ConnectionPool connectionPool)
    {
        String sql = "INSERT INTO fog.orders (username, status, totalPrice, phone_number) VALUES (?, ?, ?, ?)";
        Order order = null;

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {

            ps.setString(1, username);
            ps.setString(2, "pending");
            ps.setFloat(3, totalPrice);
            ps.setString(4, phoneNumber);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0)
            {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    int orderId = generatedKeys.getInt(1);
                    order = new Order(orderId, username,phoneNumber, "pending", totalPrice);
                } else
                {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return order;
    }


    public static List<Order> getOrdersByUsername(String username, ConnectionPool connectionPool)
    {
        String sql = "SELECT * FROM fog.orders WHERE username = ?";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {

            ps.setString(1, username);


            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    int orderId = rs.getInt("id");
                    String status = rs.getString("status");
                    String phoneNumber = rs.getString("phone_number");
                    float totalPrice = rs.getFloat("totalPrice");
                    Order order = new Order(orderId, username,phoneNumber, status, totalPrice);
                    orders.add(order);
                }
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return orders;
    }

}
