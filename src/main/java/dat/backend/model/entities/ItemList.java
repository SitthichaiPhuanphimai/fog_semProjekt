package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemList {

    private List<Item> itemList;
    private float totalPrice;

    public ItemList(List<Item> itemList) {
        this.itemList = itemList;
        float totalprice = this.totalPrice;
    }

    public ItemList(List<Item> itemList, float totalPrice) {
        this.itemList = itemList;
        this.totalPrice = totalPrice;
        
    }

    public List<Item> getItemList() {
        return this.itemList;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

    public float calculateTotalPrice(ConnectionPool connectionPool) throws SQLException, DatabaseException {

            float totalPrice = 0;
            for (Item item : itemList) {
                int quantity = item.getQuantity() > 0 ? item.getQuantity() : 1;
                float priceForWholeLength = item.getPrice() * item.getLength();
                totalPrice += priceForWholeLength * quantity;
            }

        

        System.out.println(totalPrice);
        float salesTax = totalPrice * (1 + ItemListFacade.getSalesTax(connectionPool).getValue());
        System.out.println(salesTax);
        float priceWithmoms = salesTax * ItemListFacade.getMoms(connectionPool).getValue();
        System.out.println(priceWithmoms);

        return salesTax + priceWithmoms;
    }


    @Override
    public String toString() {
        return "ItemList{" +
                "itemList=" + itemList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

