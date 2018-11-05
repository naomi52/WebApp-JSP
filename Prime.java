package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Engine;

/**
 * Servlet implementation class Prime
 */
@WebServlet("/Prime.do")
public class Prime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StringBuilder newLower;
	public String upperPrime;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if (upperPrime < newLower)
		Engine engine = Engine.getInstance();
		if (request.getParameter("primecalc1") == null && request.getParameter("primecalc2") == null) //|| request.getParameter("primecalc2") == null
		{
			
		} 
		
		
		else if (request.getParameter("primecalc2") != null)
		{		
			//you've clicked the next button
			
			request.setAttribute("y", upperPrime);
			try
			{
				request.setAttribute("result",  engine.doPrime(newLower.toString(), upperPrime));
				
				request.setAttribute("primer", newLower.toString());
				
				newLower = new StringBuilder(request.getAttribute("result").toString());
			} 
			catch (Exception e)
			{
				request.setAttribute("error", e.getMessage());
			}
		}
		
		else {
			//if youve nevcer clicked the next statement
			//Engine engine = Engine.getInstance();
			String x = request.getParameter("lowerprimeinput");
			String y = request.getParameter("upperprimeinput");
			upperPrime = y;
			request.setAttribute("x", x);
			request.setAttribute("y", upperPrime);
			try
			{
				request.setAttribute("result",  engine.doPrime(x, y));
				newLower = new StringBuilder(request.getAttribute("result").toString());
				request.setAttribute("primer", x);
				//request.setAttribute("x", request.getAttribute("result").toString());
			} 
			catch (Exception e)
			{
				request.setAttribute("error", e.getMessage());
			}
		}
		this.getServletContext().getRequestDispatcher("/Prime.jspx").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
