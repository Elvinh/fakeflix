

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class filterServlet
 */
@WebServlet("/filterServlet")
public class filterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public filterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean needJDBC(HttpServletRequest request)
	{
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String urlPattern = servletPath;
		
		if(pathInfo != null)
		{
			urlPattern = servletPath + "/*";
		}
		
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
	               .getServletRegistrations();
	 
	 
	       // Collection of all servlet in your webapp.
	       Collection<? extends ServletRegistration> values = servletRegistrations.values();
	       for (ServletRegistration sr : values) 
	       {
	           Collection<String> mappings = sr.getMappings();
	           if (mappings.contains(urlPattern)) {
	               return true;
	           }
	       }
	       return false;
		}
		

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(this.needJDBC(req))
		{
			
			System.out.println("Open Connection for: " + req.getServletPath());
			
			Connection conn = null;
			
			try
			{
				
				conn = connectionU.getConnection();
				
				conn.setAutoCommit(false);
				
				request.setAttribute("ourConnection", conn);
				chain.doFilter(request, response);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				connectionU.rollbackQuietly(conn);
				throw new ServletException();
			}
			finally
			{
				connectionU.closeQuietly(conn);
			}
		}
	}
}
