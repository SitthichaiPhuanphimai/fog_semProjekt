package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.Material;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;
import dat.backend.model.persistence.ItemMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet(name = "UpdateMaterialServlet", value = "/UpdateMaterialServlet")
public class UpdateMaterialServlet extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));
        List<Item> itemList = (List<Item>) getServletContext().getAttribute("materialList");

        Item item = null;
        for (Item i: itemList)
        {
            if(i.getId()==id)
            {
                item = i;
                break;
            }
        }
        //tilføj exception handling TODO!!
        /*if (material. == null) {
            request.setAttribute("errorMessage", "Material not found");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }*/

        request.setAttribute("material",item);

        request.getRequestDispatcher("/WEB-INF/updateMaterial.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = new ConnectionPool();

        //List<Material> materialList = (List<Material>) getServletContext().getAttribute("materialList");

        int materialID = Integer.parseInt(request.getParameter("id"));
        float newPrice = Float.parseFloat(request.getParameter("price"));

        ItemFacade.updatePrice(connectionPool, materialID, newPrice);

        request.getRequestDispatcher("/WEB-INF/materialsOverviewPage.jsp").forward(request, response);

    }
}
