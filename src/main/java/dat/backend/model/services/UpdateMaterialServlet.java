package dat.backend.model.services;

import dat.backend.model.entities.Material;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet(name = "UpdateMaterialServlet", value = "/UpdateMaterialServlet")
public class UpdateMaterialServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id = Integer.parseInt(request.getParameter("id"));
        List<Material> materialList = (List<Material>) getServletContext().getAttribute("materialList");

        Material material = null;
        for (Material m:materialList)
        {
            if(m.getId()==id)
            {
                material = m;
                break;
            }
        }
        //tilføj exception handling TODO!!
        /*if (material. == null) {
            request.setAttribute("errorMessage", "Material not found");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }*/

        request.setAttribute("material",material);

        request.getRequestDispatcher("/WEB-INF/updateMaterial.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = new ConnectionPool();

        List<Material> materialList = (List<Material>) getServletContext().getAttribute("materialList");


        int materialID = Integer.parseInt(request.getParameter("id"));
        float newPrice = Float.parseFloat(request.getParameter("price"));


        if(materialID == 0 || newPrice <= 0)
        {
            request.setAttribute("errorMessage", "Invalid input");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }else
        {
            String query = "UPDATE fog.material SET price_per_unit = ? WHERE id = ?";
            try(Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
            )
            {
                preparedStatement.setFloat(1, newPrice);
                preparedStatement.setFloat(2, materialID);
                preparedStatement.executeUpdate();

                //update materialList with new value.
                //Dette bliver nok erstattet med en refreshMaterialslist() metode her i UpdateMaterialServlet
                for (Material m : materialList) {
                    if (m.getId() == materialID) {
                        m.setPrice(newPrice);
                        break;
                    }
                }
                System.out.println("database update succes");
                request.getRequestDispatcher("/WEB-INF/materialsOverviewPage.jsp").forward(request,response);
            }catch (SQLException sqlException)
            {
                System.out.println("could not update");
                sqlException.printStackTrace();
            }
        }
    }
}
