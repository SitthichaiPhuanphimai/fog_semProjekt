package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Item;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
        HttpSession session = request.getSession();

        if (!Authentication.isUserLoggedIn(request) && !Authentication.isRoleAllowed("admin", request))
        {
            Authentication.redirectToLogin(request, response);

        } else
        {
            try
            {
                List<Item> materialList = MaterialFacade.getMaterials(connectionPool);

                getServletContext().setAttribute("materialList", materialList);


                if (session.getAttribute("successMessage") != null)
                {
                    request.setAttribute("successMessage", session.getAttribute("successMessage"));
                    session.removeAttribute("successMessage");
                }
            } catch (DatabaseException e)
            {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);

            }


            request.getRequestDispatcher("/WEB-INF/materialsOverviewPage.jsp").forward(request, response);

        }


    }
}

