package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class Auth
 */
@WebServlet("/Auth.do")
public class Auth extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //String back = "http://localhost:4413/ProjD/Admin.do";
    String logged = "hi";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Auth() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        if (request.getParameter("dCalc") != null) {
             
            String usr = request.getParameter("usr");
            String psw = request.getParameter("psw");
             
            //System.out.println(usr + " " + psw);
            //response.sendRedirect("https://www.eecs.yorku.ca/~roumani/servers/auth/oauth.cgi?"+"back=" +back );
            
            
            
            
            
            if(usr!=null && usr.equals("admin") && psw.equals("potato")) {
                // do stuff
                request.setAttribute("result", "Authenticated");
                 
                String redirectLocation = "Admin.do";
                //String backLink = "?back=" + request.getRequestURL().toString();
                //String location = redirectLocation;// + backLink;
 
                response.sendRedirect(redirectLocation);
            }
            else {
                request.setAttribute("error", "You are not an admin, what are you doing here?");
                request.getServletContext().getRequestDispatcher("/Auth.jspx").forward(request, response);
            }
             
             
        }
        else {
            request.getServletContext().getRequestDispatcher("/Auth.jspx").forward(request, response);
        }
         
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
 
} 
