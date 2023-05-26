package dat.backend.control;

import dat.backend.model.persistence.ConnectionPool;

import dat.backend.model.persistence.MaterialFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * The {@code DeleteMaterialServlet} class represents a servlet for
 * handling the deletion of materials.
 * <p>
 * It handles POST requests to delete a specified material, identified by its ID, from the database.
 *
 * @version 1.0
 */

@WebServlet(name = "DeleteMaterialServlet", value = "/DeleteMaterialServlet")
public class DeleteMaterialServlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ConnectionPool connectionPool = new ConnectionPool();
        int materialId = Integer.parseInt(request.getParameter("id"));

        MaterialFacade.deleteMaterial(connectionPool, materialId);

        HttpSession session = request.getSession();
        session.setAttribute("deleteSuccess", true);

        response.sendRedirect("ToViewMaterialsServlet");

    }
}
