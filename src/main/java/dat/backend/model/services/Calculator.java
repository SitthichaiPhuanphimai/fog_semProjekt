package dat.backend.model.services;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;
import dat.backend.model.persistence.ItemMapper;

import java.util.ArrayList;
import java.util.List;



public class Calculator {
   List<Item> itemList = new ArrayList<>();
   private float length;
   private float width;


   public Calculator(float length, float width) {
      this.length = length;
      this.width = width;
   }




public List<Item> calcSupportBeams() throws DatabaseException {
      double amountOfSupportBeamsRounded = Math.ceil(length / 0.55);

      Item supportBeamWithOptimalLength = getOptimalItem(width, "SupportBeam");


      Result result = new Result(amountOfSupportBeamsRounded, supportBeamWithOptimalLength);
      result.addItemByQuantity(itemList);

      return itemList;
   }


   public int calcBeams() {
      int calculaAmountOfBeams = (length < 5) ? 4 : 6;

      return calculaAmountOfBeams;

   }


   public List<Item> calcBraces() throws DatabaseException {

        double amountOfBraces = 2;
        Item braceWithOptimalLength = getOptimalItem(length, "Brace");
        Result result = new Result(amountOfBraces, braceWithOptimalLength);
        result.addItemByQuantity(itemList);

        return itemList;

   }

   // Float x is the value of the item you need to calculate upon, and String type is the type of item you need to calculate upon
   public Item getOptimalItem(float x, String type) throws DatabaseException {
      ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
      return ItemFacade.getOptimalItem(x, type, connectionPool);

   }




}

