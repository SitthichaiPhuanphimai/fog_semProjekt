package dat.backend.model.services;

import dat.backend.model.entities.Item;

import java.util.List;

public class Result
{
    double quantity;
    Item item;

    public Result(double quantity, Item item)
    {
        this.quantity = quantity;
        this.item = item;
    }


    public List<Item> addItemByQuantity(List<Item> itemList)
    {
        for (int i = 0; i < quantity; i++)
        {
            itemList.add(item);
        }
        return itemList;
    }
}
