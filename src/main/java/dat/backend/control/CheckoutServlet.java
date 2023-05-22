package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.ItemList;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemListFacade;
import dat.backend.model.persistence.OrderFacade;

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

        HttpSession session = request.getSession();
        float totalPrice = (float) session.getAttribute("totalPrice");
        User user = (User) session.getAttribute("user");

        if (user == null)
        {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else
        {

            String action = request.getParameter("action");
            if ("Acceptere".equals(action))
            {
                String username = user.getUsername();

                // Get item list from session
                ItemList itemList = (ItemList) session.getAttribute("itemList");
                try
                {
                    // Create order and retrieve order ID
                    Order order = OrderFacade.createOrder(username, totalPrice, connectionPool);
                    session.setAttribute("order", order);

                    // Insert items into material_list table using the returned order ID
                    ItemListFacade.createMaterialList(order.getId(), itemList, connectionPool);

                    request.getRequestDispatcher("/WEB-INF/orderDetails.jsp").forward(request, response);
                } catch (DatabaseException | SQLException e)
                {
                    // Set the error message attribute so the error page can display it
                    request.setAttribute("errorMessage", e.getMessage());
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } else
            {
                // Forward to order placement page
                request.getRequestDispatcher("/WEB-INF/orderPlacement.jsp").forward(request, response);
            }

        }
    }
}
