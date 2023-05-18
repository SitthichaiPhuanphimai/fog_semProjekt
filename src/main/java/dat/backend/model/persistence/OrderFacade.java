package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.Order;

import java.util.List;

public class OrderFacade
{


 public static Order createOrder(String username, ConnectionPool connectionPool)
 {
     return OrderMapper.createOrder(username, connectionPool);
 }


}
