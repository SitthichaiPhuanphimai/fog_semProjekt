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
        
    }

}

