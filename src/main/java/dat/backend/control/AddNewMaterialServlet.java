package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Material;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.ItemFacade;
import dat.backend.model.persistence.ItemListFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddNewMaterialServlet", value = "/AddNewMaterialServlet")
public class AddNewMaterialServlet extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/addNewMaterialPage.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (!Authentication.isUserLoggedIn(request)) {
                Authentication.redirectToLogin(request, response);
                return;
            }

            String newDescription = request.getParameter("description");
            float newPrice = 0;
            int newUnitID = 0;
            int newMaterialType = 0;
            int newMaterialLength = 0;

            try {
                newPrice = Float.parseFloat(request.getParameter("price"));
                newUnitID = Integer.parseInt(request.getParameter("unitId"));
                newMaterialType = Integer.parseInt(request.getParameter("materialType"));
                newMaterialLength = Integer.parseInt(request.getParameter("materialLength"));
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid number input");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            if (newDescription == null || newUnitID <= 0 || newPrice <= 0 || newMaterialType <= 0 || newMaterialLength <= 0) {
                request.setAttribute("errorMessage", "Invalid input");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            } else {
                ItemFacade.addNewMaterial(newDescription, newPrice, newUnitID, newMaterialType, newMaterialLength, connectionPool);

                System.out.println("Database update success");
                request.getRequestDispatcher("/WEB-INF/materialsOverviewPage.jsp").forward(request,response);
            }
        } catch (SQLException | ServletException | IOException | DatabaseException e) {
            // Log the error
            e.printStackTrace();

            // Set the error message and forward to an error page
            request.setAttribute("errorMessage", "An error occurred while processing your request");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


}
