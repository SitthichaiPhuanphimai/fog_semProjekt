package dat.backend.model.persistence;

import dat.backend.model.entities.Order;

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


        try(Connection conn = connection.getConnection())
        {
            String sql = "DELETE FROM fog.order WHERE id = ?";

            try(PreparedStatement statement = conn.prepareStatement(sql))
            {
                statement.setString(1, orderId);
                statement.executeUpdate();
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        ArrayList<Order> ordersList = OrdersMapper.getAllOrders();

        request.setAttribute("ordersList", ordersList);

        request.getRequestDispatcher("WEB-INF/viewAllOrders.jsp").forward(request,response);



    }
}
