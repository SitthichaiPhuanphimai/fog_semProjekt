package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Tax;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.TaxFacade;
import dat.backend.model.persistence.TaxMapper;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
/**
 * The {@code ViewTaxesServlet} class is responsible for handling
 * the viewing and updating of tax values in the system.
 * <p>
 * This servlet handles GET and POST HTTP requests. The GET method retrieves the existing
 * details of all taxes in the system, which are then forwarded to taxOverviewPage
 *  The POST method updates the tax value
 * based on provided user/admin input, and then forwards the updated tax list to taxOverviewPage.jsp
 *
 * @version 1.0
 */
@WebServlet(name = "ViewTaxesServlet", value = "/ViewTaxesServlet")
public class ViewTaxesServlet extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("admin")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        } else {

            List<Tax> taxList = TaxMapper.getTaxList(connectionPool);

            getServletContext().setAttribute("taxList", taxList);

            request.getRequestDispatcher("/WEB-INF/taxOverviewPage.jsp").forward(request, response);
        }
    }


    /**
     * Processes a POST request, updating the tax value based on provided user input.
     * This is done by using a static method from the {@code TaxFacade} class.
     * After updating, the updated tax list is forwarded to taxOverviewPage
     *
     * @param request  HttpServletRequest from the client
     * @param response HttpServletResponse to the client
     * @throws ServletException If a servlet exception occurs
     * @throws IOException      If an I/O exception occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int taxId = Integer.parseInt(request.getParameter("id"));
        double newTaxValue = Double.parseDouble(request.getParameter("taxValue"));

        if(taxId <= 0 || newTaxValue < 0) {
            request.setAttribute("errorMessage", "Invalid input");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else {
            try {
                TaxFacade.updateTaxValue(taxId, newTaxValue, connectionPool);
                String successMessage = "Afgiften er nu ændret.";
                request.setAttribute("successMessage", successMessage);

                List<Tax> taxList = TaxFacade.getTaxList(connectionPool);
                request.setAttribute("taxList", taxList);

                request.getRequestDispatcher("/WEB-INF/taxOverviewPage.jsp").forward(request,response);
            } catch (DatabaseException e) {
                request.setAttribute("errorMessage", "Unable to update tax value");
                request.getRequestDispatcher("/error.jsp").forward(request, response);

            }
        }
    }
}