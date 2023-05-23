package dat.backend.model.services;

import dat.backend.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Authentication
{
    public static boolean isRoleAllowed(String role, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null)
        {
            return user.getRole().equals(role);
        }
        return false;
    }


    public static User getLoggedInUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }

    public static void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public static boolean isUserLoggedIn(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user != null;
    }
}
