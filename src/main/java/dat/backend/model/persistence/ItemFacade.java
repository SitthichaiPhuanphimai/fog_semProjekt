package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

public class ItemFacade {


    public static Item getOptimalItem(float length, String type, ConnectionPool connectionPool) throws DatabaseException {
        return ItemMapper.getOptimalItem(length, type, connectionPool);
    }
}
