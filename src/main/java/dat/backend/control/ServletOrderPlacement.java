package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.services.Authentication;
import dat.backend.model.services.Calculator;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletOrderPlacement", value = "/ServletOrderPlacement")
public class ServletOrderPlacement extends HttpServlet
{

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (Authentication.isUserLoggedIn(request))
        {
            request.getRequestDispatcher("WEB-INF/orderPlacement.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!Authentication.isUserLoggedIn(request))
        {
            return;
        }

        HttpSession session = request.getSession();

        float uLength = Float.parseFloat(request.getParameter("length"));
        session.setAttribute("uLength", uLength);

        float uWidth = Float.parseFloat(request.getParameter("width"));
        session.setAttribute("uWidth", uWidth);

        // Create a Calculator instance and run calculations
        Calculator calculator = new Calculator(uLength, uWidth);
        try
        {
            calculator.RunAllCalculations(connectionPool);
            ItemList itemList = new ItemList(calculator.getItemList());
            System.out.println(itemList.toString());
            session.setAttribute("itemList", itemList);
            session.setAttribute("totalPrice", itemList.calculateTotalPrice(connectionPool));
        } catch (DatabaseException | SQLException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        String skur = request.getParameter("skur");
        if (skur.equals("ja") || skur.equals("nej"))
        {
            session.setAttribute("skur", skur.equals("ja") ? skur : null);
        }

        request.getRequestDispatcher("/WEB-INF/orderConfirmation.jsp").forward(request, response);
    }
}
