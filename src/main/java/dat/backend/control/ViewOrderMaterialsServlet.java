package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.ItemList;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ViewOrderMaterialsServlet", value = "/ViewOrderMaterials")
public class ViewOrderMaterialsServlet extends HttpServlet {
    private ConnectionPool connectionPool;
    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        List<Item> orderItems = OrderFacade.getListByOrderId(orderId, connectionPool);
        ItemList itemList = new ItemList(orderItems);

        try {
           double totalPrice = itemList.calculateTotalPrice(connectionPool);
            request.setAttribute("totalPrice", totalPrice);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.setAttribute("itemList", orderItems);
        request.getRequestDispatcher("WEB-INF/viewOrderMaterials.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
