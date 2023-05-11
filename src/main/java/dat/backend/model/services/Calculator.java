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


public void RunAllCalculations(ConnectionPool connectionPool) throws DatabaseException {
   calcSupportBeams(connectionPool);
   calcBraces(connectionPool);
   calcBeams();
}



public List<Item> calcSupportBeams(ConnectionPool connectionPool) throws DatabaseException {
      double amountOfSupportBeamsRounded = Math.ceil(length / 0.55);
        Item supportBeamWithOptimalLength = ItemFacade.getOptimalItem(length, "Spær", connectionPool);



      Result result = new Result(amountOfSupportBeamsRounded, supportBeamWithOptimalLength);
      result.addItemByQuantity(itemList);

      return itemList;
   }


   public int calcBeams() {
      int calculaAmountOfBeams = (length < 5) ? 4 : 6;

      return calculaAmountOfBeams;

   }


    public List<Item> calcBraces(ConnectionPool connectionPool) throws DatabaseException {
        double amountOfBraces = 2;
        Item braceWithOptimalLength = ItemFacade.getOptimalItem(length, "Rem", connectionPool);
        Result result = new Result(amountOfBraces, braceWithOptimalLength);
        result.addItemByQuantity(itemList);
        return itemList;
    }







}

