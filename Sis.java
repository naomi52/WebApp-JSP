package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Sis
 */
@WebServlet("/Sis.do")
public class Sis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("siscalc")==null) {
			
		}
		else {
			Engine engine = Engine.getInstance();
			String prefix = request.getParameter("prefix");
			String gpa = request.getParameter("gpa");
			String orderVal = request.getParameter("sortBy");
			//System.out.println(orderVal);
			
			request.setAttribute("prefix", prefix);
			request.setAttribute("gpa", gpa);
			request.setAttribute("sortBy", orderVal);
			
			try
			{
				request.setAttribute("result", engine.myDAO.retrieve(prefix, gpa, orderVal));
				//request.setAttribute("result",  "$" + engine.doRide(prefix, gpa));
				//System.out.println(request.getAttribute("result"));
			} 
			catch (Exception e)
			{
				request.setAttribute("error", e.getMessage());
			}
		}
		this.getServletContext().getRequestDispatcher("/Sis.jspx").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
