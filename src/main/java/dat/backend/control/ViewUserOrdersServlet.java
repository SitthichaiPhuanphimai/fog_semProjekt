package dat.backend.control;


import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.services.Authentication;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewUserOrdersServlet", value = "/ViewUserOrders")
public class ViewUserOrdersServlet extends HttpServlet
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

        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        if (!Authentication.isUserLoggedIn(request))
        {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else
        {
            String username = user.getUsername();
            try
            {
                List<Order> orders = OrderFacade.getOrdersByUsername(username, connectionPool);
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("WEB-INF/viewUserOrders.jsp").forward(request, response);
            } catch (DatabaseException e)
            {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
            }


        }
    }
}
