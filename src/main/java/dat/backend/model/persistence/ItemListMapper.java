package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.Tax;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ItemListMapper
{

    public static boolean createMaterialList(int orderId, ItemList itemList, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "INSERT INTO fog.material_list (description, material_id, order_id, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {

            // Here we combine the items with the same id into one item with a total quantity
            // this will give us a list of unique items with their total quantity
            // instead of multiple of the same items
            Map<Item, Integer> combineItems = new HashMap<>();
            for (Item item : itemList.getItemList())
            {
                combineItems.put(item, combineItems.getOrDefault(item, 0) + 1);
            }

            // Insert each unique item and its total quantity into the database
            for (Map.Entry<Item, Integer> entry : combineItems.entrySet())
            {
                Item item = entry.getKey();
                int quantity = entry.getValue();

                ps.setString(1, item.getName());
                ps.setInt(2, item.getId());
                ps.setInt(3, orderId);
                ps.setInt(4, quantity);

                ps.executeUpdate();
            }

        } catch (SQLException e)
        {
            throw new DatabaseException("Creating material list failed");
        }
        return true;
    }

    static Tax getSalesTax(ConnectionPool connectionPool)
    {
        Tax tax = null;
        String sql = "SELECT * FROM fog.taxes WHERE tax_name = 'sales_tax'";

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("tax_name");
                    float taxPercentage = rs.getFloat("tax_value");

                    tax = new Tax(id, taxPercentage, name);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tax;
    }


    static Tax getMoms(ConnectionPool connectionPool)
    {
        Tax tax = null;
        String sql = "SELECT * FROM fog.taxes WHERE tax_name = 'moms'";

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    int id = rs.getInt("id");
                    String name = rs.getString("tax_name");
                    float taxPercentage = rs.getFloat("tax_value");

                    tax = new Tax(id, taxPercentage, name);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tax;
    }
}
