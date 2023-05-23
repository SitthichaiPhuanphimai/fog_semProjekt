package dat.backend.model.persistence;

import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.Tax;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.SQLException;

public class ItemListFacade {

    public static boolean createMaterialList(int orderId, ItemList itemList, ConnectionPool connectionPool) throws SQLException, DatabaseException {
      return ItemListMapper.createMaterialList(orderId, itemList, connectionPool);

    }

    public static Tax getSalesTax(ConnectionPool connectionPool) throws SQLException, DatabaseException {
      return ItemListMapper.getSalesTax(connectionPool);
    }

    public static Tax getMoms(ConnectionPool connectionPool) throws SQLException, DatabaseException {
      return ItemListMapper.getMoms(connectionPool);
    }
}
