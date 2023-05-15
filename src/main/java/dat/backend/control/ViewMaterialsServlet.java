package dat.backend.control;

import dat.backend.model.entities.Material;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ViewMaterialsServlet", value = "/ViewMaterialsServlet")
public class ViewMaterialsServlet extends HttpServlet {

    public void init() {
        List<Material> materialList = new ArrayList<>();

        getServletContext().setAttribute("materialList", materialList);

        ConnectionPool connectionPool = new ConnectionPool();

        String query = "SELECT * FROM fog.material";
            try(Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
            )
            {
                while(resultSet.next())
                {
                    int id = resultSet.getInt("id");
                    String description = resultSet.getString("description");
                    int price = resultSet.getInt("price_per_unit");
                    int unitId = resultSet.getInt("unit_id");
                    int materialType = resultSet.getInt("material_type_id");
                    int materialLength = resultSet.getInt("material_length_id");

                    Material material = new Material(id,description,price,unitId,materialType,materialLength);

                    materialList.add(material);
                }

            }catch (SQLException sqlException)
            {
                System.out.println("problem with setup of materials");
                sqlException.printStackTrace();
            }

        }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/materialsOverviewPage.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
