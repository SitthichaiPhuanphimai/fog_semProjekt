package dat.backend.control;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ToViewMaterialsServlet", value = "/ToViewMaterialsServlet")
public class ToViewMaterialsServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("ViewMaterialsServlet").forward(request, response);
    }

}
