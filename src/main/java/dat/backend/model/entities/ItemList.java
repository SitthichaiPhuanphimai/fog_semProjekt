package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemList {

    HashMap<String, ArrayList<String>> map = new HashMap<>();
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
            totalPrice += (item.getPrice() * item.getLength());
        }

        double salesTax = totalPrice * ItemListFacade.getSalesTax(connectionPool).getValue();
        double priceWithmoms = salesTax * ItemListFacade.getMoms(connectionPool).getValue();




        return totalPrice + (float) priceWithmoms + (float) salesTax;
    }


}

