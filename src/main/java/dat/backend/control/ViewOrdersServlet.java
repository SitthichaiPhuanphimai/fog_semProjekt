package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "viewOrdersServlet", value = "/viewOrdersServlet")
public class ViewOrdersServlet extends HttpServlet
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
        try
        {
            String orderId = request.getParameter("orderId");
            String newStatus = request.getParameter("status");

            OrdersMapper.updateOrderStatus(orderId, newStatus, connectionPool);


            List<Order> ordersList = OrdersMapper.getAllOrders(connectionPool);


            request.setAttribute("ordersList", ordersList);

            request.getRequestDispatcher("WEB-INF/viewAllOrders.jsp").forward(request, response);
        } catch (DatabaseException e)
        {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
        }
    }

}