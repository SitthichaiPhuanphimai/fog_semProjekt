package dat.backend.model.entities;

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

    public float calculateTotalPrice() {
        float totalPrice = 0;
        for (Item item : itemList) {
            totalPrice += (item.getPrice() * item.getLength());
        }
        return totalPrice;
    }


}

