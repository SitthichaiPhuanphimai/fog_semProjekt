package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper {

    static Item getOptimalItem(float length, String type, ConnectionPool connectionPool) throws DatabaseException {

     String sql = "SELECT fog.material.description, fog.material.price_per_unit, fog.material.length, fog.material_type.type\n" +
             "FROM fog.material\n" +
             "INNER JOIN fog.material_type\n" +
             "ON fog.material.material_type_id = fog.material_type.id\n" +
             "WHERE fog.material.length >= ? AND fog.material_type.type = ?\n" +
             "ORDER BY fog.material.length ASC LIMIT 1;";

     // Skal jeg escape ? i type med single quotes, fordi det er en String i SQL?

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setFloat(1, length);
                ps.setString(2, type);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                  String name = rs.getString("description");
                  float price = rs.getFloat("price_per_unit");
                  float lengthofItem = rs.getFloat("length");

                    Item item = new Item(name, lengthofItem, price);
                    return item;
                } else {
                    throw new DatabaseException("No item found");
                }
            }
        } catch (SQLException | DatabaseException ex) {
            throw new DatabaseException(ex, "Error getting item. Something went wrong with the database");
        }










    }
}
