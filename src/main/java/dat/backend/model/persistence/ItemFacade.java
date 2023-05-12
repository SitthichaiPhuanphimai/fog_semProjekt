package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public class ItemFacade {


    public static List<Item> getOptimalItem(float length, String type, ConnectionPool connectionPool) throws DatabaseException, SQLException {
        return ItemMapper.getOptimalItem(length, type, connectionPool);
    }
}
