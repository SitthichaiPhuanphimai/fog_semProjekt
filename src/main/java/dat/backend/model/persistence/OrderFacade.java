package dat.backend.model.persistence;

import dat.backend.model.entities.Item;
import dat.backend.model.entities.Order;

import java.util.List;

public class OrderFacade
{


    public static Order createOrder(String username, float totalPrice, ConnectionPool connectionPool) {
        return OrderMapper.createOrder(username, totalPrice, connectionPool);
    }

    public static List<Order> getOrdersByUsername(String username, ConnectionPool connectionPool) {
        return OrderMapper.getOrdersByUsername(username, connectionPool);
    }

    public static List<Item> getListByOrderId(int orderId, ConnectionPool connectionPool) {
        return OrdersMapper.getItemList(orderId, connectionPool);
    }

}
