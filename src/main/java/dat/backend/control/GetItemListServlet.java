package dat.backend.control;

import dat.backend.model.entities.Item;
import dat.backend.model.services.Authentication;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "getItemListServlet", value = "/getItemListServlet")
public class GetItemListServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (Authentication.isUserLoggedIn(request))
        {
            processGetItemList(request, response);
        } else
        {
            Authentication.redirectToLogin(request, response);
        }
    }

    private void processGetItemList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        ConnectionPool connection = new ConnectionPool();

        ArrayList<Item> itemList = OrdersMapper.getItemList(orderId, connection);

        request.setAttribute("itemList", itemList);
        request.getRequestDispatcher("WEB-INF/viewItemList.jsp").forward(request, response);
    }
}
