package dat.backend.model.persistence;

import dat.backend.model.entities.Material;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper
{

    public static List<Material> getMaterials()
    {
        List<Material> materialList = new ArrayList<>();

        ConnectionPool connectionPool = new ConnectionPool();

        String query = "SELECT * FROM fog.material";
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);)
        {
            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                float price = resultSet.getFloat("price_per_unit");
                int unitId = resultSet.getInt("unit_id");
                int materialType = resultSet.getInt("material_type_id");
                int materialLength = resultSet.getInt("material_length_id");

                Material material = new Material(id, description, price, unitId, materialType, materialLength);

                materialList.add(material);
            }

        } catch (SQLException sqlException)
        {
            System.out.println("problem with setup of materials");
            sqlException.printStackTrace();
        }
        return materialList;
    }
}
