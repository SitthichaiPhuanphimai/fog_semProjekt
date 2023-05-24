package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public class ItemFacade {


    public static List<Item> getOptimalItem(float length, String type, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        return ItemMapper.getOptimalItem(length, type, connectionPool);
    }

    public static List<Item> getMaterial(String type, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        return ItemMapper.getMaterial(type, connectionPool);
    }

    public static void updatePrice(ConnectionPool connectionPool, int ID, float newPrice)
    {
        ItemMapper.editPrice(connectionPool, ID, newPrice);
    }

    public static Item addNewMaterial(String newDescription, float newPrice, int newUnitID, int newMaterialType, int newMaterialLength, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        return ItemMapper.addNewMaterial(newDescription, newPrice, newUnitID, newMaterialType, newMaterialLength, connectionPool);
    }
}
