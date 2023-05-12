package dat.backend.model.persistence;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemMapper {


    static List<Item> getOptimalItem(float requiredLength, String type, ConnectionPool connectionPool) throws DatabaseException, SQLException {

        String sql = "SELECT fog.material.description, fog.material.price_per_unit, fog.material_length.length, fog.material_type.type " +
                "FROM fog.material " +
                "INNER JOIN fog.material_type ON (fog.material.material_type_id = fog.material_type.id) " +
                "INNER JOIN fog.material_length ON fog.material.material_length_id = fog.material_length.id " +
                "WHERE fog.material_type.type LIKE ? " +
                "ORDER BY fog.material_length.length DESC;";

        List<Item> materials = new ArrayList<>();
        List<Item> selectedMaterials = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            double totalLength = 0;

            ps.setString(2, "%" + type + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String description = rs.getString("description");
                    float price = rs.getFloat("price_per_unit");
                    float lengthofItem = rs.getFloat("length");
                    String itemType = rs.getString("type");

                    Item item = new Item(description, lengthofItem, price, itemType);
                    materials.add(item);

                }
                // We are here checking if we have enough length of the item to cover the required length
                for (Item item : materials) {
                    if (totalLength >= requiredLength) {
                        break;
                    }
                        selectedMaterials.add(item);
                        totalLength += item.getLength();
                    }

                    // We are sorting the selected list until we have the optimal length with minimal waste
                    while (!selectedMaterials.isEmpty() && totalLength - selectedMaterials.get(selectedMaterials.size() - 1).getLength() > requiredLength) {
                        totalLength -= selectedMaterials.get(selectedMaterials.size() - 1).getLength();
                        selectedMaterials.remove(selectedMaterials.size() - 1);
                    }

                }

            } catch (SQLException ex) {
                throw new DatabaseException(ex, "Error getting item. Something went wrong with the database");
            }

return selectedMaterials;
    }
}

