package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Drone
 */
@WebServlet("/Drone.do")
public class Drone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		if(request.getParameter("dronecalc")==null) {
			
		}
		else {
			Engine engine = Engine.getInstance();
			String firstAdd = request.getParameter("startAdd");
			String secondAdd = request.getParameter("destAdd");
			
			request.setAttribute("startAdd", firstAdd);
			request.setAttribute("destAdd", secondAdd);
			
			try
			{
				request.setAttribute("result",  engine.doDrone(firstAdd, secondAdd) + " min");
				//System.out.println(request.getAttribute("result"));
			} 
			catch (Exception e)
			{
				request.setAttribute("error", e.getMessage());
			}
		}
		this.getServletContext().getRequestDispatcher("/Drone.jspx").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
