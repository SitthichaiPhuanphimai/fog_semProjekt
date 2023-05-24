package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;
import dat.backend.model.persistence.MaterialFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet
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

            if (!Authentication.isUserLoggedIn(request))
            {
                Authentication.redirectToLogin(request, response);
                return;
            }
            try
            {
                String newDescription = request.getParameter("description");
                float newPrice = Float.parseFloat(request.getParameter("price"));
                int newUnitID = Integer.parseInt(request.getParameter("unitId"));
                int newMaterialType = Integer.parseInt(request.getParameter("materialType"));
                int newMaterialLength = Integer.parseInt(request.getParameter("materialLength"));

                if (newDescription == null || newUnitID <= 0 || newPrice <= 0 || newMaterialType <= 0 || newMaterialLength <= 0)
                {
                    request.setAttribute("errorMessage", "Invalid input");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }else
                {
                    ItemFacade.addNewMaterial(newDescription, newPrice, newUnitID, newMaterialType, newMaterialLength, connectionPool);

                    // Refresh the materials list
                    List<Item> updatedMaterialsList = MaterialFacade.getMaterials(connectionPool);
                    request.getServletContext().setAttribute("materialList", updatedMaterialsList);

                    System.out.println("Database update success");
                    request.getRequestDispatcher("ToViewMaterialsServlet").forward(request,response);
                }
            }catch (SQLException | ServletException | IOException | DatabaseException e)
            {
                // Log the error
                e.printStackTrace();
                // Set the error message and forward to an error page
                request.setAttribute("errorMessage", "An error occurred while processing your request");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try
        {
            if (!Authentication.isUserLoggedIn(request))
            {
                Authentication.redirectToLogin(request, response);
                return;
            }
            try
            {
                String newDescription = request.getParameter("description");
                float newPrice = Float.parseFloat(request.getParameter("price"));
                int newUnitID = Integer.parseInt(request.getParameter("unitId"));
                int newMaterialType = Integer.parseInt(request.getParameter("materialType"));
                int newMaterialLength = Integer.parseInt(request.getParameter("materialLength"));

                if (newDescription == null || newUnitID <= 0 || newPrice <= 0 || newMaterialType <= 0 || newMaterialLength <= 0)
                {
                    request.setAttribute("errorMessage", "Invalid input");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }else
                {
                    ItemFacade.addNewMaterial(newDescription, newPrice, newUnitID, newMaterialType, newMaterialLength, connectionPool);

                    // Refresh the materials list
                    List<Item> updatedMaterialsList = MaterialFacade.getMaterials(connectionPool);
                    request.getServletContext().setAttribute("materialList", updatedMaterialsList);

                    System.out.println("Database update success");
                    request.getRequestDispatcher("ToViewMaterialsServlet").forward(request,response);
                }
            }catch (SQLException | ServletException | IOException | DatabaseException e)
            {
                // Log the error
                e.printStackTrace();
                // Set the error message and forward to an error page
                request.setAttribute("errorMessage", "An error occurred while processing your request");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }catch(NumberFormatException nfE)
        {
            nfE.printStackTrace();
        }
    }

}
