package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewMaterialsServlet", value = "/ViewMaterialsServlet")
public class ViewMaterialsServlet extends HttpServlet
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
        }

        try
        {
            List<Item> materialList = MaterialFacade.getMaterials(connectionPool);
            request.setAttribute("materialList", materialList);
            request.getRequestDispatcher("/WEB-INF/materialsOverviewPage.jsp").forward(request, response);
        } catch (DatabaseException e)
        {


            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
        }
    }
}
