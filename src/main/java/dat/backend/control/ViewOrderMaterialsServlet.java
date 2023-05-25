package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.services.Authentication;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewOrderMaterialsServlet", value = "/ViewOrderMaterials")
public class ViewOrderMaterialsServlet extends HttpServlet
{
    private ConnectionPool connectionPool;

    @Override
    public void init()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        if (!Authentication.isUserLoggedIn(request))
        {
            Authentication.redirectToLogin(request, response);

        } else
        {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            try
            {
                List<Item> orderItems = OrderFacade.getListByOrderId(orderId, connectionPool);


                request.setAttribute("itemList", orderItems);
                request.getRequestDispatcher("WEB-INF/viewOrderMaterials.jsp").forward(request, response);
            } catch (DatabaseException e)
            {
                // Handle database error here
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }

        }
    }
}

