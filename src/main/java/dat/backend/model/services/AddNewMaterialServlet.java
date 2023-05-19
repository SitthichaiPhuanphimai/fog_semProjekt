package dat.backend.model.services;

import dat.backend.model.entities.Material;
import dat.backend.model.persistence.ConnectionPool;

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/addNewMaterialPage.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = new ConnectionPool();

        List<Material> materialList = (List<Material>) getServletContext().getAttribute("materialList");

        String newDescription = request.getParameter("description");
        float newPrice = Float.parseFloat(request.getParameter("price"));
        int newUnitID = Integer.parseInt(request.getParameter("unitId"));
        int newMaterialType = Integer.parseInt(request.getParameter("materialType"));
        int newMaterialLength = Integer.parseInt(request.getParameter("materialLength"));



            if(newDescription == null || newUnitID == 0 || newPrice <= 0 || newMaterialType == 0 || newMaterialLength == 0)
            {
                request.setAttribute("errorMessage", "Invalid input");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }else
            {
                String query = "INSERT INTO fog.material (description, price_per_unit, unit_id, material_type_id, material_length_id) VALUES(?,?,?,?,?)";
                try(Connection connection = connectionPool.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                )
                {
                    preparedStatement.setString(1, newDescription);
                    preparedStatement.setFloat(2, newPrice);
                    preparedStatement.setInt(3, newUnitID);
                    preparedStatement.setInt(4, newMaterialType);
                    preparedStatement.setInt(5, newMaterialLength);

                    preparedStatement.executeUpdate();

                    System.out.println("database update succes");
                    request.getRequestDispatcher("/WEB-INF/materialsOverviewPage.jsp").forward(request,response);

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }


}
