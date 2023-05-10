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

        String h = request.getParameter("height");
        double uHeight = Double.parseDouble(h);
        session.setAttribute("uHeight", uHeight);


        String w = request.getParameter("width");
        double uWidth = Double.parseDouble(w);
        session.setAttribute("uWidth", uWidth);

        String skur = request.getParameter("skur");

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
