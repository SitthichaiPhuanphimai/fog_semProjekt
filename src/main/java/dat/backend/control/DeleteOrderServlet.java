package dat.backend.control;

import dat.backend.model.entities.Order;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;


@WebServlet(name = "deleteOrderServlet", value = "/deleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String orderId = request.getParameter("orderId");
        ConnectionPool connection = new ConnectionPool();

        OrdersMapper.deleteOrder(orderId,connection);


        ArrayList<Order> ordersList = OrdersMapper.getAllOrders(connection);

        request.setAttribute("ordersList", ordersList);

        request.getRequestDispatcher("WEB-INF/viewAllOrders.jsp").forward(request,response);



    }
}
