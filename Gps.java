package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Gps
 */
@WebServlet("/Gps.do")
public class Gps extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("gpscalc")== null) {
			
		}
		else {
			Engine engine = Engine.getInstance();
			String latFrom = request.getParameter("fromLat");
			String latTo = request.getParameter("toLat");
			String longFrom = request.getParameter("fromLong");
			String longTo = request.getParameter("toLong");
			
			request.setAttribute("fromLat", latFrom);
			request.setAttribute("toLat", latTo);
			request.setAttribute("fromLong", longFrom);
			request.setAttribute("toLong", longTo);
			
			try
			{
				request.setAttribute("result",  engine.doGps(latFrom, longFrom, latTo, longTo) + " km");
				//System.out.println(request.getAttribute("result"));
			} 
			catch (Exception e)
			{
				request.setAttribute("error", e.getMessage());
			}
		}
		this.getServletContext().getRequestDispatcher("/Gps.jspx").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
