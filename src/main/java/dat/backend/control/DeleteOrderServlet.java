package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
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

@WebServlet(name = "deleteOrderServlet", value = "/deleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet
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
            processOrderDeletion(request, response);
        } else
        {
            Authentication.redirectToLogin(request, response);
        }
    }

    private void processOrderDeletion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        OrdersMapper.deleteOrder(orderId, connectionPool);
        ArrayList<Order> ordersList = OrdersMapper.getAllOrders(connectionPool);
        request.setAttribute("ordersList", ordersList);
        request.getRequestDispatcher("WEB-INF/viewAllOrders.jsp").forward(request, response);
    }
}
