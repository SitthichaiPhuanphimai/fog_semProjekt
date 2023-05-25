package dat.backend.control;

import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "DeleteMaterialServlet", value = "/DeleteMaterialServlet")
public class DeleteMaterialServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

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
            //request.getRequestDispatcher("/WEB-INF/materialsOverview.jsp").forward(request,response);
        } catch (SQLException sqlException)
        {
            System.out.println("could not delete");
            sqlException.printStackTrace();
        }

    }
}
