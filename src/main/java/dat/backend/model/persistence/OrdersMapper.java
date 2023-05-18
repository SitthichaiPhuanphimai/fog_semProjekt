package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.Order;

import java.sql.*;
import java.util.ArrayList;

public class OrdersMapper {


    public static ArrayList<Order> getAllOrders(ConnectionPool connectionPool) {
        String sql = "SELECT * FROM orders";
        ArrayList<Order> ordersList = new ArrayList<>();


        try (Connection conn = connectionPool.getConnection();
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

    public static void deleteOrder(String id, ConnectionPool connectionPool) {
        try (Connection conn = connectionPool.getConnection()) {

            String sql = "DELETE FROM orders WHERE id = ?";


            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Item> getItemList(String id, ConnectionPool connectionPool) {
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
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
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

        } catch (SQLException e) {
            System.out.println("Error in the database");
        }

        return itemsList;
    }

    public static void updateOrder(String orderID, String status, ConnectionPool connectionPool) {
        try (Connection conn = connectionPool.getConnection()) {
            String sql = "UPDATE orders SET status = ? WHERE id = ?";

            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, status);
                statement.setString(2, orderID);
                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error in the database");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}