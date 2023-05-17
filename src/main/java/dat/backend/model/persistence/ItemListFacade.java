package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.SQLException;

public class ItemListFacade {

    public static boolean createMaterialList(int orderId, ItemList itemList, ConnectionPool connectionPool) throws SQLException, DatabaseException {
      return ItemListMapper.createMaterialList(orderId, itemList, connectionPool);
    }
}
