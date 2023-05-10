package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet
{


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");

        try
        {
            ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmpassword");

            ArrayList<User> userList = UserFacade.getAllUser(connectionPool);


            // These two lines are to remove the existing error messages if the user tries to sign up again
            request.getSession().removeAttribute("userExists");
            request.getSession().removeAttribute("passwordMismatch");

            for (User u : userList) {
                if (username.toLowerCase().equals(u.getUsername().toLowerCase())) {
                    request.getSession().setAttribute("userExists", "User already exists");
                    response.sendRedirect("signup.jsp");
                    return;

                }
            }
            if (!password.equals(confirmPassword))
            {
                request.getSession().setAttribute("passwordMismatch", "Password is not identical, please try again");
                System.out.println("Password is not identical, please try again");
                response.sendRedirect("signup.jsp");
                return;
            }
            //Creates our user object with the variables from our request.getParameter and set it to our
            //createUser method in UserFacade so that it gets created to our DB right away
            User user = UserFacade.createUser(username, password, "customer", connectionPool);


            //"user" = current user when logged in
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);


        } catch (DatabaseException e)
        {
            e.printStackTrace();
        }
    }
}