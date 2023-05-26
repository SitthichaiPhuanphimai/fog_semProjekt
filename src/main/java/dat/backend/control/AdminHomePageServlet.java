package dat.backend.control;

import dat.backend.model.entities.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * The {@code AdminHomePageServlet} class is a HttpServlet that handles GET requests.
 * <p>
 * This servlet is to direct the user to the admin page if the user is logged in and has the role 'admin'.
 * Otherwise, it redirects to login page.
 * <p>
 * @version 1.0
 */
@WebServlet(name = "HomePageServlet", value = "/AdminHomePageServlet")
public class AdminHomePageServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !user.getRole().equals("admin"))
        {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else
        {
            request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
        }
    }
}
