package dat.backend.control;


import dat.backend.model.entities.Item;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name="getItemListServlet",value = "/getItemListServlet")
public class GetItemListServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String orderId = request.getParameter("orderId");
        ConnectionPool connection = new ConnectionPool();

       OrdersMapper.getItemList(orderId,connection);

        ArrayList<Item> itemList =  OrdersMapper.getItemList(orderId,connection);

        request.setAttribute("itemList", itemList);

        request.getRequestDispatcher("WEB-INF/viewItemList.jsp").forward(request,response);

    }
}
