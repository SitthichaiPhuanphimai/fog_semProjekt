package dat.backend.control;

import com.mysql.cj.exceptions.ConnectionIsClosedException;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "viewOrdersServlet", value = "/viewOrdersServlet")
public class ViewOrdersServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse respponse) throws ServletException, IOException
    {

        ArrayList<Order> ordersList = OrdersMapper.getAllOrders();

        request.setAttribute("ordersList", ordersList);

        request.getRequestDispatcher("WEB-INF/viewAllOrders.jsp").forward(request,respponse);






    }

}
