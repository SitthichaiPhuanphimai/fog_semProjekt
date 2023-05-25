package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CheckoutServlet", value = "/Checkout")
public class CheckoutServlet extends HttpServlet
{

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!Authentication.isUserLoggedIn(request))
        {
            Authentication.redirectToLogin(request, response);
            return;
        }

        User user = Authentication.getLoggedInUser(request);
        String action = request.getParameter("action");

        try
        {
            if ("Acceptere".equals(action))
            {
                acceptOrder(request, response, user);
            } else
            {
                redirectToOrderPlacement(request, response);
            }

        } catch (DatabaseException | SQLException e)
        {
            handleException(request, response, e);
        }
    }

    private void acceptOrder(HttpServletRequest request, HttpServletResponse response, User user) throws DatabaseException, SQLException, ServletException, IOException
    {
        HttpSession session = request.getSession();
        float totalPrice = (float) session.getAttribute("totalPrice");

        String username = user.getUsername();

        // Get item list from session
        ItemList itemList = (ItemList) session.getAttribute("itemList");

        // Create order and retrieve order ID
        Order order = OrderFacade.createOrder(username, totalPrice, connectionPool);
        session.setAttribute("order", order);

        // Insert items into material_list table using the returned order ID
        ItemListFacade.createMaterialList(order.getId(), itemList, connectionPool);

        request.getRequestDispatcher("/WEB-INF/orderDetails.jsp").forward(request, response);
    }

    private void redirectToOrderPlacement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/orderPlacement.jsp").forward(request, response);
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException
    {
        // Set the error message attribute so the error page can display it
        request.setAttribute("errorMessage", e.getMessage());
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}