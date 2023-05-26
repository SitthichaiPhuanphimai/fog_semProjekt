package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * The {@code UpdateMaterialServlet} class is responsible for handling
 * the update operations related to materials in the system.
 * <p>
 * This servlet handles both GET and POST HTTP requests. GET request is used for
 * fetching the existing details of the material which is to be updated, while POST
 * request is used for submitting the updated details of the material.
 *
 * @version 1.0
 */

@WebServlet(name = "UpdateMaterialServlet", value = "/UpdateMaterialServlet")
public class UpdateMaterialServlet extends HttpServlet
{

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {


        int id = Integer.parseInt(request.getParameter("id"));
        List<Item> itemList = (List<Item>) getServletContext().getAttribute("materialList");

        Item item = null;
        for (Item i : itemList)
        {
            if (i.getId() == id)
            {
                item = i;
                break;
            }
        }
        if (item == null)
        {
            request.setAttribute("errorMessage", "Material not found");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("material", item);

        request.getRequestDispatcher("WEB-INF/updateMaterial.jsp").forward(request, response);

    }

    /**
     * Processes a POST request, by retrieving the updated price of the material from the request and the ID of the
     * material, and then updating the price of the material in the database.
     * @param request  HttpServletRequest from the client
     * @param response HttpServletResponse to the client
     * @throws ServletException If a servlet exception occurs
     * @throws IOException      If an I/O exception occurs
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ConnectionPool connectionPool = new ConnectionPool();

        int materialID = Integer.parseInt(request.getParameter("id"));
        float newPrice = Float.parseFloat(request.getParameter("price"));

        ItemFacade.updatePrice(connectionPool, materialID, newPrice);

        response.sendRedirect("ToViewMaterialsServlet");

    }
}
