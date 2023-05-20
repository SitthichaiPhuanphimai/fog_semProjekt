package dat.backend.model.services;


import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;


import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Calculator {
    private List<Item> itemList = new ArrayList<>();
    private float length;
    private float width;
    private boolean hasShed;


    public Calculator(float length, float width) {
        this.length = length;
        this.width = width;
    }


    public List<Item> getItemList() {
        return itemList;
    }


    public void RunAllCalculations(ConnectionPool connectionPool) throws DatabaseException, SQLException
    {
        calcSupportBeams(connectionPool);
        calcBraces(connectionPool);
        calcBeams(connectionPool);
        calcRoofPanel(connectionPool);
        calcScrewBox(connectionPool);
        calcHollowBand(connectionPool);
        getUniversalFitting(connectionPool);
        calcUpperSternBoardFront(connectionPool);
        calcUpperSternBoardSide(connectionPool);
        calcLowerSternBoardSide(connectionPool);
        calcLowerSternBoardWidth(connectionPool);
        calcroofGutter(connectionPool);

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
        List<Item> beamWithOptimalLength = ItemFacade.getMaterial("søjler", connectionPool);

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


    public List<Item> calcRoofPanel(ConnectionPool connectionPool) throws DatabaseException, SQLException {

        double amountOfPanelsOnWidth = width / 1.09;

        List<Item> roofPanelWithOptimalLength = ItemFacade.getOptimalItem(length, "Tagplader", connectionPool);

        if (roofPanelWithOptimalLength.isEmpty()) {
            throw new DatabaseException("No roofPanels where found in the database");
        }

        for (int i = 0; i < amountOfPanelsOnWidth; i++) {
            for (Item item : roofPanelWithOptimalLength) {
                itemList.add(item);
            }
        }

        return itemList;
    }

    public List<Item> calcScrewBox(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        List<Item> nailBox = ItemFacade.getNailBox("Skruer", connectionPool);
        int amountOfNailBox = 0;

        if (length < 5) {
            amountOfNailBox = 1;
        } else {
            amountOfNailBox = 2;
        }

        if (nailBox.isEmpty()) {
            throw new DatabaseException("No nailBox where found in the database");
        }

        for (int i = 0; i < amountOfNailBox; i++) {
            for (Item item : nailBox) {
                itemList.add(item);
            }
        }

        return itemList;
    }

    // to calculate how many HollowBand we need to use, we calculate the square root of the length^2 + width^2
    // Then we get the diagonal
    public List<Item> calcHollowBand(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        float diagonal = (float) Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));

        List<Item> hollowBand = ItemFacade.getOptimalItem(diagonal, "Hulbånd", connectionPool);

        if (hollowBand.isEmpty()) {
            throw new DatabaseException("No hollowBand where found in the database");
        }
        // We need to add the hollowBand twice, because we need to cross them from the one corner to the other
        for (int i = 0; i < 2; i++) {
            for (Item item : hollowBand) {
                itemList.add(item);
            }
        }
        return itemList;
    }

    public List<Item> getUniversalFitting(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        List<Item> universalFitting = ItemFacade.getMaterial("Universal beslag", connectionPool);

        if (universalFitting.isEmpty()) {
            throw new DatabaseException("No universalFitting where found in the database");
        }

        for (Item item : universalFitting) {
            itemList.add(item);
        }

        return itemList;
    }


    public List<Item> calcUpperSternBoardFront(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        List<Item> upperSternBoardFront = ItemFacade.getOptimalItem(width, "Oversternsbrædde til forenden", connectionPool);

        if (upperSternBoardFront.isEmpty()) {
            throw new DatabaseException("No upperSternBoardFront where found in the database");
        }

        for (Item item : upperSternBoardFront) {
            itemList.add(item);
        }
        return itemList;

    }

    public List<Item> calcUpperSternBoardSide(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        List<Item> upperSternBoardSide = ItemFacade.getOptimalItem(length, "Oversternbrædde til siderne", connectionPool);

        if (upperSternBoardSide.isEmpty()) {
            throw new DatabaseException("No upperSternBoardSide where found in the database");
        }

        for (int i = 0; i < 2; i++) {
            for (Item item : upperSternBoardSide) {
                itemList.add(item);
            }
        }
        return itemList;
    }


    public List<Item> calcLowerSternBoardWidth(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        List<Item> lowerSternBoardWidth = ItemFacade.getOptimalItem(width, "Understernsbrædde til for & bagende", connectionPool);

        if (lowerSternBoardWidth.isEmpty()) {
            throw new DatabaseException("No lowerSternBoardFront where found in the database");
        }

        for (int i = 0; i < 2; i++) {
            for (Item item : lowerSternBoardWidth) {
                itemList.add(item);
            }
        }
        return itemList;
    }


    public List<Item> calcLowerSternBoardSide(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        List<Item> lowerSternBoardSide = ItemFacade.getOptimalItem(length, "Understernsbrædde til siderne", connectionPool);

        if (lowerSternBoardSide.isEmpty()) {
            throw new DatabaseException("No lowerSternBoardSide where found in the database");
        }

        for (int i = 0; i < 2; i++) {
            for (Item item : lowerSternBoardSide) {
                itemList.add(item);
            }
        }
        return itemList;
    }

    public List<Item> calcroofGutter(ConnectionPool connectionPool) throws DatabaseException, SQLException {
        List<Item> roofGutter = ItemFacade.getOptimalItem(width, "Tagrende", connectionPool);

        if (roofGutter.isEmpty()) {
            throw new DatabaseException("No roofGutter where found in the database");
        }


            for (Item item : roofGutter) {
                itemList.add(item);
            }

        return itemList;
    }

}












