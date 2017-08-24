

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		Cookie loginCookie1 = null;
		Cookie loginCookie2 = null;
		Cookie[] cookies = request.getCookies();
		
		
		if(cookies != null)
		{
			for(Cookie cookie: cookies)
			{
				if(cookie.getName().equals("first_name"))
				{
					loginCookie1 = cookie;
					
				}
				if(cookie.getName().equals("last_name"))
				{
					loginCookie2 = cookie;
					break;
				}
			}
			
		}
		
		if(loginCookie1 != null && loginCookie2 != null)
		{
			loginCookie1.setMaxAge(0);
			loginCookie2.setMaxAge(0);
			response.addCookie(loginCookie1);
			response.addCookie(loginCookie2);
		}
		
		response.sendRedirect("main.jsp");
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
