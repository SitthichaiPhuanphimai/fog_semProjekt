package dat.backend.control;

import dat.backend.model.persistence.ConnectionPool;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code DeleteMaterialServlet} class represents a servlet for
 * handling the deletion of materials.
 * <p>
 * It handles POST requests to delete a specified material, identified by its ID, from the database.
 *
 * @version 1.0
 */

@WebServlet(name = "DeleteMaterialServlet", value = "/DeleteMaterialServlet")
public class DeleteMaterialServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = new ConnectionPool();
        int materialID = Integer.parseInt(request.getParameter("id"));

        String query = "DELETE FROM fog.material WHERE id = ?";
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
        )
        {
            preparedStatement.setInt(1, materialID);
            preparedStatement.executeUpdate();

            response.sendRedirect("ToViewMaterialsServlet");
        } catch (SQLException sqlException)
        {
            System.out.println("could not delete");
            sqlException.printStackTrace();
        }

    }
}
