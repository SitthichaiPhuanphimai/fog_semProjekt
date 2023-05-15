package dat.backend.model.services;


import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Calculator {
    private List<Item> itemList = new ArrayList<>();
    private float length;
    private float width;


    public Calculator(float length, float width) {
        this.length = length;
        this.width = width;
    }


    public List<Item> getItemList() {
        return itemList;
    }


    public void RunAllCalculations(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        calcSupportBeams(connectionPool);
        calcBraces(connectionPool);
        calcBeams(connectionPool);
    }

// Adding the support beams to the list amountOfSupportBeamsRounded times
    public List<Item> calcSupportBeams(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        double amountOfSupportBeamsRounded = Math.ceil(length / 0.55);
        List<Item> supportBeamWithOptimalLength = ItemFacade.getOptimalItem(width, "Spær", connectionPool);

        if (supportBeamWithOptimalLength.isEmpty()) {
            throw new DatabaseException("No supportBeams where found in the database");
        }

        for (int i = 0; i < amountOfSupportBeamsRounded; i++) {
            for (Item item : supportBeamWithOptimalLength) {
                itemList.add(item);
            }
        }

        return itemList;
    }

    public int calcBeams(ConnectionPool connectionPool) throws SQLException, DatabaseException {
        int calculaAmountOfBeams = (length < 5) ? 4 : 6;
        List<Item> beamWithOptimalLength = ItemFacade.getBraces( "søjler", connectionPool);

        if (beamWithOptimalLength.isEmpty()) {
            throw new DatabaseException("No beams where found in the database");
        }

        for (int i = 0; i < calculaAmountOfBeams; i++) {
            itemList.addAll(beamWithOptimalLength);
        }

        return calculaAmountOfBeams;
    }

    // Adding the braces to the list amountOfBraces times
    public List<Item> calcBraces(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        double amountOfBraces = 2;
        List<Item> braceWithOptimalLength = ItemFacade.getOptimalItem(length, "Rem", connectionPool);

        if (braceWithOptimalLength.isEmpty()) {
            throw new DatabaseException("No braces where found in the database");
        }

        for (int i = 0; i < amountOfBraces; i++) {
            for (Item item : braceWithOptimalLength) {
                itemList.add(item);
            }
        }

        return itemList;
    }





}








