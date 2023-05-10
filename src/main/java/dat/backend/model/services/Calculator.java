package dat.backend.model.services;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
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
   private Result calculatedBeams;
   private Result calculatedSupportBeams;
   private Result calculatedBraces;


   public Calculator(float length, float width) {
      this.length = length;
      this.width = width;
   }


   public float getLength() {
      return length;
   }

   public void setLength(float length) {
      this.length = length;
   }

   public float getWidth() {
      return width;
   }

   public void setWidth(float width) {
      this.width = width;
   }

   public Result getCalculatedBeams() {
      return calculatedBeams;
   }

   public void setCalculatedBeams(Result calculatedBeams) {
      this.calculatedBeams = calculatedBeams;
   }

   public Result getCalculatedSupportBeams() {
      return calculatedSupportBeams;
   }

   public void setCalculatedSupportBeams(Result calculatedSupportBeams) {
      this.calculatedSupportBeams = calculatedSupportBeams;
   }

   public Result getCalculatedBraces() {
      return calculatedBraces;
   }

   public void setCalculatedBraces(Result calculatedBraces) {
      this.calculatedBraces = calculatedBraces;
   }



public Result calcSupportBeams() throws DatabaseException {
      double amountOfSupportBeamsRounded = Math.ceil(length / 0.55);

      float supportBeamLength = getOptimalItem(length, "SupportBeam").getLength();


      Result result = new Result(amountOfSupportBeamsRounded, supportBeamLength);

      return result;
   }


   public int calcBeams() {
      int calculaAmountOfBeams = (length < 5) ? 4 : 6;

      return calculaAmountOfBeams;

   }


   public Result calcBraces() throws DatabaseException {


return null;
   }

   // Float x is the value of the item you need to calculate upon, and String type is the type of item you need to calculate upon
   public Item getOptimalItem(float x, String type) throws DatabaseException {
      ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
      return ItemFacade.getOptimalItem(x, type, connectionPool);

   }


}

