package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class MaterialFacade {

    public static List<Item> getMaterials(ConnectionPool connectionPool) throws DatabaseException {
        return MaterialMapper.getMaterials(connectionPool);
    }
}
