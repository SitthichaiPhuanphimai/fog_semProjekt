package dat.backend.control;

import dat.backend.model.entities.Material;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "ViewMaterialsServlet", value = "/ViewMaterialsServlet")
public class ViewMaterialsServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Material> materialList = MaterialMapper.getMaterials();

        getServletContext().setAttribute("materialList", materialList);

        request.getRequestDispatcher("/WEB-INF/materialsOverviewPage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
