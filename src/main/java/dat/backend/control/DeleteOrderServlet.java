package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;


@WebServlet(name = "deleteOrderServlet", value = "/deleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
    private ConnectionPool connectionPool;
    @Override
    public void init()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {

           int orderId = Integer.parseInt(request.getParameter("orderId"));


            OrdersMapper.deleteOrder(orderId, connectionPool);


            ArrayList<Order> ordersList = OrdersMapper.getAllOrders(connectionPool);

            request.setAttribute("ordersList", ordersList);

            request.getRequestDispatcher("WEB-INF/viewAllOrders.jsp").forward(request, response);


        }
    }

}