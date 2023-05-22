package dat.backend.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class MaterialMapperTest
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

                stmt.execute("insert into fog_test.material (description, price_per_unit,unit_id,material_type_id,material_length_id) " +
                        "values ('testMaterial1',35,1,1,1),('testMaterial2',50,2,2,5)");


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
   void testMaterialListNotEmpty() throws DatabaseException
   {
       List<Item> materialList = MaterialFacade.getMaterials(connectionPool);
       Assertions.assertNotNull(materialList);
       Assertions.assertFalse(materialList.isEmpty());


   }

   @Test
   void testGetCorrectMaterials() throws DatabaseException {

       List<Item> materialList = MaterialFacade.getMaterials(connectionPool);

       String expectedDesc = "testMaterial1";
       int expectedPrice = 35;
       String expectedUnit = "Meter";
       String expectedType = "Spær til tag";


       String expectedDesc2 = "testMaterial2";
       int expectedPrice2 = 50;
       String expectedUnit2 = "Box";
       String expectedType2 = "Rem til at stå på stolpe, og støtte tag";


       Item material1 = materialList.get(0);
       Assertions.assertEquals(expectedDesc, material1.getDescription());
       Assertions.assertEquals(expectedPrice, material1.getPrice());
       Assertions.assertEquals(expectedUnit, material1.getUnit());
       Assertions.assertEquals(expectedType, material1.getType());

       Item material2 = materialList.get(1);
       Assertions.assertEquals(expectedDesc2, material2.getDescription());
       Assertions.assertEquals(expectedPrice2, material2.getPrice());
       Assertions.assertEquals(expectedUnit2, material2.getUnit());
       Assertions.assertEquals(expectedType2, material2.getType());





   }



}
