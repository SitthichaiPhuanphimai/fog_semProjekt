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

/**
 * The {@code SignUpServlet} class is responsible for handling the user sign up requests.
 * <p>
 * This servlet handles POST requests. The request is used for processing the sign-up data
 * including username, password and password confirmation.
 * <p>
 * The servlet checks if the user already exists and if the password matches the confirmation password.
 * If the validation is successful, it creates a new user in the system and forwards the request to the
 * home page.
 *
 * @version 1.0
 */

@WebServlet(name = "SignUpServlet", value = "/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmpassword");

            ArrayList<User> userList = UserFacade.getAllUser(connectionPool);

            // Remove existing error messages
            request.getSession().removeAttribute("userExists");
            request.getSession().removeAttribute("passwordMismatch");

            if (userExists(username, userList)) {
                request.getSession().setAttribute("userExists", "User already exists");
                response.sendRedirect("signup.jsp");
                return;
            }

            if (!password.equals(confirmPassword)) {
                request.getSession().setAttribute("passwordMismatch", "Password is not identical, please try again");
                System.out.println("Password is not identical, please try again");
                response.sendRedirect("signup.jsp");
                return;
            }

            User user = UserFacade.createUser(username, password, "customer", connectionPool);

            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);

        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if a user already exists in the system.
     *
     * @param username The username to check
     * @param userList The list of existing users
     * @return {@code true} if the user already exists, {@code false} otherwise
     */
    private boolean userExists(String username, ArrayList<User> userList) {
        for (User user : userList) {
            if (username.equalsIgnoreCase(user.getUsername())) {
                return true;
            }
        }
        return false;
    }
}
