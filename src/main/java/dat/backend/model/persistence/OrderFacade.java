package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;

import java.util.List;

public class OrderFacade {


    public static List<Item> createOrderList(String username, ItemList itemList, ConnectionPool connectionPool) {
        return OrdersMapper.createOrderList(username, itemList, connectionPool);
    }
}
