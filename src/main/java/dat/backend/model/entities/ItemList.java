package dat.backend.model.entities;

import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;
import java.sql.SQLException;
import java.util.List;

public class ItemList
{

    private List<Item> itemList;
    private float totalPrice;

    public ItemList(List<Item> itemList)
    {
        this.itemList = itemList;
        float totalprice = this.totalPrice;
    }

    public ItemList(List<Item> itemList, float totalPrice)
    {
        this.itemList = itemList;
        this.totalPrice = totalPrice;

    }

    public List<Item> getItemList()
    {
        return this.itemList;
    }

    public float getTotalPrice()
    {
        return this.totalPrice;
    }

    public float calculateTotalPrice(ConnectionPool connectionPool) throws SQLException, DatabaseException
    {

        float totalPrice = 0;
        for (Item item : itemList) {
            totalPrice += (item.getPrice() * item.getLength());
        }

        float totalPriceWithSalesTax = totalPrice * (1 + ItemListFacade.getSalesTax(connectionPool).getValue());

        float priceWithMoms = totalPriceWithSalesTax * ItemListFacade.getMoms(connectionPool).getValue();


        return totalPriceWithSalesTax + priceWithMoms;
    }


    @Override
    public String toString()
    {
        return "ItemList{" +
                "itemList=" + itemList +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

