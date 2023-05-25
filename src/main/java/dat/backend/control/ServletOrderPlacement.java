package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.ItemList;
import dat.backend.model.services.Authentication;
import dat.backend.model.services.Calculator;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
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
        // Initialize database connection pool
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (Authentication.isUserLoggedIn(request))
        {
            request.getRequestDispatcher("WEB-INF/orderPlacement.jsp").forward(request, response);
        } else
        {
            // Redirect to login page if user not authenticated
            Authentication.redirectToLogin(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!Authentication.isUserLoggedIn(request))
        {
            Authentication.redirectToLogin(request, response);
            return;
        }

        try
        {
            processRequest(request, response);
        } catch (DatabaseException | SQLException e)
        {
            // Provide a specific error message for the exception
            request.setAttribute("errorMessage", "An error occurred while processing your request. Error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

   // This method is to process all the backend logic for the order placement. It is called from the doPost method above.
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DatabaseException, SQLException
    {
        HttpSession session = request.getSession();

        float uLength = Float.parseFloat(request.getParameter("length"));
        float uWidth = Float.parseFloat(request.getParameter("width"));

        session.setAttribute("uLength", uLength);
        session.setAttribute("uWidth", uWidth);

        // Create a Calculator instance and run calculations
        Calculator calculator = new Calculator(uLength, uWidth);
        calculator.RunAllCalculations(connectionPool);
        ItemList itemList = new ItemList(calculator.getItemList());
        session.setAttribute("itemList", itemList);
        session.setAttribute("totalPrice", itemList.calculateTotalPrice(connectionPool));

        String skur = request.getParameter("skur");
        if ("ja".equals(skur) || "nej".equals(skur))
        {
            session.setAttribute("skur", "ja".equals(skur) ? skur : null);
        }

        request.getRequestDispatcher("/WEB-INF/orderConfirmation.jsp").forward(request, response);
    }
}