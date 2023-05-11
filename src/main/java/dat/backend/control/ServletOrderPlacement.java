package dat.backend.control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletOrderPlacement", value = "/ServletOrderPlacement")
public class ServletOrderPlacement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("WEB-INF/orderPlacement.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String l = request.getParameter("length");
        //Double uLength = Double.parseDouble(l);
        //session.setAttribute("uLength", uLength);

        String w = request.getParameter("width");
        //Double uWidth = Double.parseDouble(w);
        //session.setAttribute("uWidth", uWidth);

        String skur = request.getParameter("skur");


        if(l == null || l.isEmpty() || w == null || w.isEmpty() || skur == null || skur.isEmpty()) {
            // One of the parameters is not set, redirect to an error page or show an error message
            request.setAttribute("errorMessage", "Please choose a value for all fields");
            request.getRequestDispatcher("WEB-INF/orderPlacement.jsp").forward(request,response);
            return;
        }

        try {
            Double uLength = Double.parseDouble(l);
            session.setAttribute("uLength", uLength);

            Double uWidth = Double.parseDouble(w);
            session.setAttribute("uWidth", uWidth);
        } catch (NumberFormatException e) {
            // The length or width parameter was not a valid double
            request.setAttribute("errorMessage", "Invalid length or width");
            request.getRequestDispatcher("WEB-INF/orderPlacement.jsp").forward(request,response);
            return;
        }




        if(skur.equals("ja"))
        {
            session.setAttribute("skur",skur);
        }else if(skur.equals("nej"))
        {
            session.setAttribute("skur",null);
        }

        request.getRequestDispatcher("/WEB-INF/orderConfirmation.jsp").forward(request,response);

    }
}
