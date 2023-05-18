package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialMapper
{

   static List<Item> getMaterials(ConnectionPool connectionPool) throws DatabaseException {
    String sql = "SELECT fog.material.id,fog.material.description, fog.material.price_per_unit, fog.material_length.length, fog.material_type.type, fog.unit.unit " +
            "FROM fog.material " +
            "INNER JOIN fog.material_type ON (fog.material.material_type_id = fog.material_type.id) " +
            "INNER JOIN fog.material_length ON fog.material.material_length_id = fog.material_length.id " +
            "INNER JOIN fog.unit ON fog.material.unit_id = fog.unit.id ";


    List<Item> materials = new ArrayList<>();

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {



    try (ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id");
            String description = rs.getString("description");
            float price = rs.getFloat("price_per_unit");
            float lengthofItem = rs.getFloat("length");
            String unit = rs.getString("unit");
            String itemType = rs.getString("type");


            Item item = new Item(id, description, lengthofItem, price, unit, itemType);
            materials.add(item);

        }
    } catch (SQLException ex) {
        throw new DatabaseException(ex, "Error getting item. Something went wrong with the database");
    }
} catch (SQLException | DatabaseException ex) {
    throw new DatabaseException(ex, "Error getting item. Something went wrong with the database");
}

        return materials;
}
}
