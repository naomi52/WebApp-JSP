package adhoc;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import filter.FakeReq;

/**
 * Servlet Filter implementation class October
 */
//@WebFilter({ "/October", "/Ride.do", filterName = "October", urlPatterns = {"/Sis.do"}, initParams = {@WebInitParam(name="sortBy", value="sortBy")}})
//@WebFilter({ "/October", "/Ride.do", "/Sis.do" })
public class October implements Filter {

    /**
     * Default constructor. 
     */
    public October() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("In Halt, before");
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest rqst = (HttpServletRequest) request;
		
		resp.setContentType("text/html");
		
		//resp.getWriter().write("Feature not available ... <a href=\"Dash.do\">Dashboard</a>");
		if(rqst.getServletPath().contains("Ride")) {
			resp.setContentType("text/html");
			resp.getWriter().write("Feature not available ... <a href=\"Dash.do\">Dashboard</a>");
		}
		
		
		
		if(rqst.getServletPath().contains("Sis")) {
			if(rqst.getParameter("sortBy") == null) {
				chain.doFilter(rqst, resp);
			}
			else if(!(rqst.getParameter("sortBy").equals("NONE"))) {
				resp.setContentType("text/html");

		        resp.getWriter().write("Feature not available ... <a href=\"Dash.do\">Dashboard</a>");
				
			}
			else {
				chain.doFilter(rqst, resp);
			}
		}
		
		System.out.println("In Halt, after");
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
