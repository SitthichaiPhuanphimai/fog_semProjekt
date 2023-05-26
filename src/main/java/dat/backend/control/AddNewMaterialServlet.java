package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.*;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;
import dat.backend.model.services.Authentication;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * The {@code AddNewMaterialServlet} class is a HttpServlet handling both GET and POST requests.
 * <p>
 * For GET requests, it retrieves data related to Unit types, Length types, and types
 * from the database and forwards them to addNewMaterialPage.jsp.
 * <p>
 * For POST requests, it adds a new material with details provided in the request parameters
 * to the database. After adding the new material, it retrieves the updated material list
 * and redirects to ToViewMaterialsServlet.
 * <p>
 * It also includes security checks, redirecting to login if the user is not logged in or doesn't have
 * 'admin' role.
 * @version 1.0
 */
@WebServlet(name = "AddNewMaterialServlet", value = "/AddNewMaterialServlet")
public class AddNewMaterialServlet extends HttpServlet
{

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            List<UnitDefault> unitTypes = DefaultFacade.getUnitTypes(connectionPool);
            request.setAttribute("unitTypes", unitTypes);

            List<LengthDefault> lengthTypes = DefaultFacade.getLengthTypes(connectionPool);
            request.setAttribute("lengthTypes", lengthTypes);

            List<TypeDefault> types = DefaultFacade.getTypes(connectionPool);
            request.setAttribute("types", types);

            request.getRequestDispatcher("/WEB-INF/addNewMaterialPage.jsp").forward(request, response);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (DatabaseException e)
        {
            request.setAttribute("errorMessage", "Could not retrieve defaults from the database");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            e.printStackTrace();
        }
    }


/**
 * Adds a new material with details provided in the request parameters
 * to the database. After adding the new material, it retrieves the updated material list
 * and redirects to ToViewMaterialsServlet.
 * <p>
 * Includes security checks, redirecting to login if the user is not logged in or doesn't have
 * 'admin' role.
 */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (!Authentication.isUserLoggedIn(request) && Authentication.isRoleAllowed("admin", request))
        {
            Authentication.redirectToLogin(request, response);
            return;
        }
        try
        {
            String newDescription = request.getParameter("description");
            float newPrice = Float.parseFloat(request.getParameter("price"));
            int newUnitID = Integer.parseInt(request.getParameter("unitId"));
            int newMaterialType = Integer.parseInt(request.getParameter("typeId"));
            int newMaterialLength = Integer.parseInt(request.getParameter("lengthId"));

            if (newDescription == null || newUnitID <= 0 || newPrice <= 0 || newMaterialType <= 0 || newMaterialLength <= 0)
            {
                request.setAttribute("errorMessage", "Invalid input");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } else
            {
                ItemFacade.addNewMaterial(newDescription, newPrice, newUnitID, newMaterialType, newMaterialLength, connectionPool);


                List<Item> updatedMaterialsList = MaterialFacade.getMaterials(connectionPool);
                request.getServletContext().setAttribute("materialList", updatedMaterialsList);

                HttpSession session = request.getSession();
                session.setAttribute("successMessage", "Nyt materiale er nu tilføjet til databasen");


                response.sendRedirect("ToViewMaterialsServlet");
            }
        } catch (SQLException | ServletException | IOException | DatabaseException e)
        {
            e.printStackTrace();

            request.setAttribute("errorMessage", "An error occurred while processing your request");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


}
