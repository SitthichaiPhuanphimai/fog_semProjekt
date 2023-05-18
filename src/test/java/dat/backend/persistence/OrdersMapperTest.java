package dat.backend.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class OrdersMapperTest {
    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://164.92.165.237:3306/fog_test?user=dev";

    private static ConnectionPool connectionPool;

    @BeforeAll
    public static void setupClass() {
        connectionPool = new ConnectionPool(USER, PASSWORD, URL);
        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement st = testConnection.createStatement()) {

                st.execute("CREATE TABLE IF NOT EXISTS fog_test.orders LIKE fog.orders;");

            }

        } catch (SQLException e) {
            System.out.println("Database connection failed");
        }

    }

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {

                // TODO: Remove all rows from all tables - add your own tables here
                stmt.execute("delete from fog_test.orders");

                // TODO: Insert a few users - insert rows into your own tables here
                stmt.execute("insert into fog_test.orders (username, status) " +
                        "values ('testUser','pending'),('testUser2','pending')");


            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }


    @Test
    void testConnection() throws SQLException {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }


    @Test
    void testGetRightOrders() {

        ArrayList<Order> ordersList = OrdersMapper.getAllOrders(connectionPool);

        String expectedUsername = "testUser";
        String expectedStatus = "pending";

        String expectedUsername2 = "testUser2";
        String expectedStatus2 = "pending";

        Order firstOrder = ordersList.get(0);
        Assertions.assertEquals(expectedUsername, firstOrder.getUsername());
        Assertions.assertEquals(expectedStatus, firstOrder.getStatus());

        Order secondOrder = ordersList.get(1);
        Assertions.assertEquals(expectedUsername2, secondOrder.getUsername());
        Assertions.assertEquals(expectedStatus2, secondOrder.getStatus());

    }

    @Test
    void testOrdersListNotEmpty() {

        ArrayList<Order> ordersList = OrdersMapper.getAllOrders(connectionPool);

        Assertions.assertNotNull(ordersList);
        Assertions.assertFalse(ordersList.isEmpty());

    }

    @Test
    void testDeleteOrder() {

        try (Connection testConnection = connectionPool.getConnection()) {
            try (Statement st = testConnection.createStatement()) {

                st.execute("INSERT INTO fog_test.orders (username, status) VALUES ('testUser3','pending')");

                ArrayList<Order> ordersList = OrdersMapper.getAllOrders(connectionPool);

                String orderId = String.valueOf(ordersList.get(2).getId());

                OrdersMapper.deleteOrder(orderId,connectionPool);

                int newOrderList = OrdersMapper.getAllOrders(connectionPool).size();

                assertEquals(2,newOrderList);

            }

        } catch (SQLException e) {
            System.out.println("Database connection failed");
        }


    }
}