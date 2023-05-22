package dat.backend.model.services;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest
{

    private final static String USER = "dev";
    private final static String PASSWORD = "3r!DE32*/fDe";
    private final static String URL = "jdbc:mysql://164.92.165.237:3306/fog_test?user=dev";

    private static ConnectionPool connectionPool = new ConnectionPool(USER,PASSWORD,URL);

    @BeforeEach
    void setUp()
    {
        try (Connection testConnection = connectionPool.getConnection())
        {
            try (Statement stmt = testConnection.createStatement())
            {
                stmt.execute("delete from fog_test.material");

                stmt.execute("INSERT into fog_test.material SELECT * FROM fog.material;");


            }
        }
        catch (SQLException throwables)
        {
            System.out.println(throwables.getMessage());
            fail("Database connection failed");
        }
    }

    @Test
    void testConnection() throws SQLException
    {
        Connection connection = connectionPool.getConnection();
        assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void calcSupportBeams() throws SQLException, DatabaseException
    {
        Calculator calc = new Calculator(5,4);

        calc.calcSupportBeams(connectionPool);
        assertNotNull(calc.getItemList());
        assertEquals(20,calc.getItemList().size());

    }

    @Test
    void getOptimalItem()
    {

    }


    //Test that calcScrewBox only returns 1 screwbox if length < 5
    @Test
    void calcScrewBox1() throws SQLException, DatabaseException
    {

        Calculator calc = new Calculator(3,3);
        List<Item>itemList = calc.calcScrewBox(connectionPool);
        assertEquals(1,itemList.size());

    }

    //Test that calcScrewBox only returns 2 screwbox if length > 5
    @Test
    void calcScrewBox2() throws SQLException, DatabaseException
    {
        Calculator calc = new Calculator(6,6);
        List<Item>itemList = calc.calcScrewBox(connectionPool);
        assertEquals(2,itemList.size());


    }
}