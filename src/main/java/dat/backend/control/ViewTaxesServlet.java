package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Tax;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.TaxMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ViewTaxesServlet", value = "/ViewTaxesServlet")
public class ViewTaxesServlet extends HttpServlet {
    private ConnectionPool connectionPool;
    @Override
    public void init()
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("admin")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {

            List<Tax> taxList = TaxMapper.getTaxList();

            getServletContext().setAttribute("taxList", taxList);

            request.getRequestDispatcher("/WEB-INF/taxOverviewPage.jsp").forward(request, response);


        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int taxId = Integer.parseInt(request.getParameter("id"));
        double newTaxValue = Double.parseDouble(request.getParameter("taxValue"));

        if(taxId <= 0 || newTaxValue < 0) // check her om id og value er passende
        {
            request.setAttribute("errorMessage", "Invalid input");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        } else
        {
            try
            {
                TaxMapper.updateTaxValue(taxId, newTaxValue);
                System.out.println("Tax value update success");

                getServletContext().setAttribute("taxList", TaxMapper.getTaxList());

                request.getRequestDispatcher("/WEB-INF/taxOverviewPage.jsp").forward(request,response);
            } catch (Exception e)
            {
                request.setAttribute("errorMessage", "Unable to update tax value");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                e.printStackTrace();
            }
        }
    }
 }

