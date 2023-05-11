package dat.backend.model.persistence;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper {


    static Item getOptimalItem(float length, String type, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "SELECT fog.material.description, fog.material.price_per_unit, fog.material_length.length, fog.material_type.type " +
                "FROM fog.material " +
                "INNER JOIN fog.material_type ON (fog.material.material_type_id = fog.material_type.id) " +
                "INNER JOIN fog.material_length ON fog.material.material_length_id = fog.material_length.id " +
                "WHERE fog.material_length.length >= ? " +
                "AND fog.material_type.type LIKE ? " +
                "ORDER BY fog.material_length.length ASC " +
                "LIMIT 1;";




        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setFloat(1, length);
            ps.setString(2, "%" + type + "%");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String description = rs.getString("description");
                    float price = rs.getFloat("price_per_unit");
                    float lengthofItem = rs.getFloat("length");
                    String itemType = rs.getString("type");

                    return new Item(description, lengthofItem, price, itemType);
                } else {
                    throw new DatabaseException("No item found");
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Error getting item. Something went wrong with the database");
        }

    }

}

