package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper
{

    static List<Item> getMaterials(ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "SELECT material.id,material.description, material.price_per_unit, material_length.length, material_type.type, unit.unit " +
                "FROM material " +
                "INNER JOIN material_type ON (material.material_type_id = material_type.id) " +
                "INNER JOIN material_length ON material.material_length_id = material_length.id " +
                "INNER JOIN unit ON material.unit_id = unit.id ";


        List<Item> materials = new ArrayList<>();

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
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

    static boolean deleteMaterial(ConnectionPool connectionPool, int materialId)
    {
        String query = "DELETE FROM fog.material WHERE id = ?";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);)
        {
            preparedStatement.setInt(1, materialId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1)
            {
                return true;
            } else
            {
                throw new DatabaseException("The material with id = " + materialId + " could not be deleted");
            }
        } catch (SQLException | DatabaseException sqlException)
        {
            System.out.println("could not delete");
            sqlException.printStackTrace();
            return false;
        }

    }
}
