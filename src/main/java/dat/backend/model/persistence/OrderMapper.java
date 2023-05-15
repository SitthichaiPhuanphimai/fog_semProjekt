package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {


    public static ArrayList<Order> getAllOrders() {
        String sql = "SELECT * FROM fog.order";
        ArrayList<Order> ordersList = new ArrayList<>();
        ConnectionPool connection = new ConnectionPool();

        try (Connection conn = connection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String status = rs.getString("Status");

                Order order = new Order(id, username, status);
                ordersList.add(order);
            }


        } catch (SQLException s) {
            System.out.println("Error in the database");
        }

        return ordersList;
    }


    public static Order createOrder(String username, ConnectionPool connectionPool) {
        String sql = "INSERT INTO fog.order (username, status) VALUES (?, ?)";
        Order order = null;

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, username);
            ps.setString(2, "pending");

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    order = new Order(orderId, username, "pending");
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

}