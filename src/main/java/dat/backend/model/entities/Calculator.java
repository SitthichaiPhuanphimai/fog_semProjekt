package dat.backend.model.entities;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
   List<Item> itemList = new ArrayList<>();
   private float lenght;
   private float width;
   private int calculatedBeams;
   private float calculatedSupportBeams;
   private float calculatedBraces;



   public Calculator(float lenght, float width){
      this.lenght = lenght;
      this.width = width;
   }

   public void runAll(){



   }

   public int calcBeams() {
      int calculaAmountOfBeams = (lenght<5)? 4:6;

      return calculaAmountOfBeams;

   }



}


