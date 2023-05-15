package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.services.Calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletOrderPlacement", value = "/ServletOrderPlacement")
public class ServletOrderPlacement extends HttpServlet {

    private ConnectionPool connectionPool;

@Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("WEB-INF/orderPlacement.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String h = request.getParameter("height");
        float uHeight = Float.parseFloat(h);   // parse as float
        session.setAttribute("uHeight", uHeight);


        String w = request.getParameter("width");
        float uWidth = Float.parseFloat(w);    // parse as float
        session.setAttribute("uWidth", uWidth);

        // Create a Calculator instance and run calculations
        Calculator calculator = new Calculator(uHeight, uWidth);
        try {
            calculator.RunAllCalculations(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ItemList itemList =new ItemList(calculator.getItemList());

        session.setAttribute("itemList",itemList.getItemList());

        String skur = request.getParameter("skur");

        if (skur.equals("ja")) {
            session.setAttribute("skur", skur);
        } else if (skur.equals("nej")) {
            session.setAttribute("skur", null);
        }

        request.getRequestDispatcher("testListPopulation.jsp").forward(request, response);

    }
}
