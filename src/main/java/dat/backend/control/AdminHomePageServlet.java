package dat.backend.control;

import dat.backend.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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
