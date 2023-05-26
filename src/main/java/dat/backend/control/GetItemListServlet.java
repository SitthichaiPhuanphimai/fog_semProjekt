package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.services.Authentication;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The {@code GetItemListServlet} class is responsible for retrieving
 * the list of items for a specific order.
 * <p>
 * It handles POST requests to fetch the item list of a specified order, identified by its ID.
 * After fetching the list, it forwards the request to the 'viewItemList' page.
 *

 * @version 1.0

 */

@WebServlet(name = "getItemListServlet", value = "/getItemListServlet")
public class GetItemListServlet extends HttpServlet
{
    private ConnectionPool connectionPool;

    @Override
    public void init()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (Authentication.isUserLoggedIn(request))
        {
            try
            {
                processGetItemList(request, response);
            } catch (DatabaseException e)
            {
                e.printStackTrace();
            }
        } else
        {
            Authentication.redirectToLogin(request, response);
        }
    }

    /**
     * Fetches the item list of a specified order and forwards the request to the 'viewItemList' page.
     *
     * @param request  HttpServletRequest from the client
     * @param response HttpServletResponse to the client
     * @throws ServletException If a servlet exception occurs
     * @throws IOException      If an I/O exception occurs
     */

    private void processGetItemList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DatabaseException
    {
        try
        {
        int orderId = Integer.parseInt(request.getParameter("orderId"));


        ArrayList<Item> itemList = OrdersMapper.getItemList(orderId, connectionPool);

        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("WEB-INF/viewItemList.jsp").forward(request, response);
    }catch (SQLException e)
        {
            throw new DatabaseException(e, "Error while fetching item list from database.");
    }
    }
}
