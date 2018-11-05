package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin.do")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Engine engine = Engine.getInstance();
		
		 if(request.getParameter("calc") != null) {
             
	            try {
	                String opt = request.getParameter("option");
	                request.setAttribute("option", opt);
	                 
	                request.setAttribute("result", engine.analyze(opt));
	            }
	            catch(Exception e) {
	                request.setAttribute("error", e.getMessage());
	            }
	        }
	 
	        request.getServletContext().getRequestDispatcher("/Admin.jspx").forward(request, response);
	 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
