package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemMapper
{

    static List<Item> getOptimalItem(float requiredLength, String type, ConnectionPool connectionPool) throws DatabaseException, SQLException
    {
        String sql = "SELECT material.id,material.description, material.price_per_unit, material_length.length, material_type.type, unit.unit " +
                "FROM material " +
                "INNER JOIN material_type ON (material.material_type_id = material_type.id) " +
                "INNER JOIN material_length ON material.material_length_id = material_length.id " +
                "INNER JOIN unit ON material.unit_id = unit.id " +
                "WHERE material_type.type LIKE ?;";


        List<Item> materials = new ArrayList<>();


        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setString(1, "%" + type + "%");

            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price_per_unit");
                    float lengthOfItem = rs.getFloat("length");
                    String unit = rs.getString("unit");
                    String itemType = rs.getString("type");


                    Item item = new Item(id, description, lengthOfItem, price, unit, itemType);
                    materials.add(item);

                }
            } catch (SQLException ex)
            {
                throw new DatabaseException(ex, "Error getting item. Something went wrong with the database");
            }
        }


        // Convert requiredLength to an integer to avoid dealing with floating point numbers
        int target = (int) (requiredLength * 100);

        // filling out the dp array with the maximum value of a float. as we iterate through the array we will replace the values with the optimal solution
        float[] dp = new float[target + 1];
        Arrays.fill(dp, Float.MAX_VALUE);
        // Starting point for our dynamic programming
        dp[0] = 0;

        // Initialize parent array to reconstruct the solution
        int[] parent = new int[target + 1];

        // Dynamic programming
        for (int j = 1; j <= target; j++)
        {
            for (int i = 0; i < materials.size(); i++)
            {
                Item item = materials.get(i);
                int itemLength = (int) (item.getLength() * 100);
                if (itemLength <= j)
                {
                    float waste = dp[j - itemLength] + item.getLength();
                    if (waste < dp[j])
                    {
                        dp[j] = waste;
                        parent[j] = i;
                    }
                }
            }
        }

        // Reconstruct solution
        List<Item> selectedMaterials = new ArrayList<>();
        for (int j = target; j > 0; j -= (int) (materials.get(parent[j]).getLength() * 100))
        {
            if (parent[j] < materials.size())
            {
                selectedMaterials.add(materials.get(parent[j]));
            }
        }

        return selectedMaterials;
    }

    static List<Item> getMaterial(String type, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT fog.material.id,fog.material.description, fog.material.price_per_unit, fog.material_length.length, fog.material_type.type, fog.unit.unit " +
                "FROM fog.material " +
                "INNER JOIN fog.material_type ON (fog.material.material_type_id = fog.material_type.id) " +
                "INNER JOIN fog.material_length ON fog.material.material_length_id = fog.material_length.id " +
                "INNER JOIN fog.unit ON fog.material.unit_id = fog.unit.id " +
                "WHERE fog.material_type.type LIKE ?;";

        List<Item> materials = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql))
        {

            ps.setString(1, "%" + type + "%");

            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price_per_unit");
                    float lengthofItem = rs.getFloat("length");
                    String unit = rs.getString("unit");
                    String itemType = rs.getString("type");


                    Item item = new Item(id, description, lengthofItem, price, unit, itemType);
                    materials.add(item);
                }
            } catch (SQLException ex)
            {
                throw new DatabaseException(ex, "Error getting item. Something went wrong with the database");
            }
        } catch (SQLException | DatabaseException ex)
        {
            throw new DatabaseException(ex, "Error getting item. Something went wrong with the database");
        }

        return materials;
    }

    static Item addNewMaterial(String newDescription, float newPrice, int newUnitID, int newMaterialType, int newMaterialLength, ConnectionPool connectionPool) throws DatabaseException, SQLException
    {
        String query = "INSERT INTO fog.material (description, price_per_unit, unit_id, material_type_id, material_length_id) VALUES(?,?,?,?,?)";

        Item item = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(query))
        {
            ps.setString(1, newDescription);
            ps.setFloat(2, newPrice);
            ps.setInt(3, newUnitID);
            ps.setInt(4, newMaterialType);
            ps.setInt(5, newMaterialLength);


            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1)
            {
                item = new Item(newUnitID, newDescription, newMaterialLength, newPrice, String.valueOf(newUnitID), String.valueOf(newMaterialType));
            } else
            {
                throw new DatabaseException("The material with description = " + newDescription + " could not be inserted into the database");
            }
        } catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return item;
    }


    static void editPrice(ConnectionPool connectionPool, int materialID, float newPrice)
    {

        //List<Material> materialList = (List<Material>) getServletContext().getAttribute("materialList");


        if (materialID == 0 || newPrice < 0)
        {
            System.out.println("material not found or price invalid");
        } else
        {
            String query = "UPDATE fog.material SET price_per_unit = ? WHERE id = ?";
            try (Connection connection = connectionPool.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query);)
            {
                preparedStatement.setFloat(1, newPrice);
                preparedStatement.setFloat(2, materialID);
                preparedStatement.executeUpdate();


                System.out.println("database update succes");
            } catch (SQLException sqlException)
            {
                System.out.println("could not update");
                sqlException.printStackTrace();
            }
        }

    }

}



