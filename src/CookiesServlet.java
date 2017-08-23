

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class CookiesServlet
 */
@WebServlet("/Cookies")
public class CookiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/*
	 * 
	 * 
	 * 							CHOCOLATE CHIP COOKIES
	 * 
	 */
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookiesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		if(session.getAttribute("loginedU") != null)
		{
			session.setAttribute("Cookie_Checked", "Checked");
			chain.doFilter(request, response);
			return;
		}
		
		Connection conn = (Connection) request.getAttribute("ourConnection");
		
		
		String checked = (String) session.getAttribute("Cookie_Checked");
		String usersFName = (String) session.getAttribute("userFName");
		String usersLName = (String) session.getAttribute("userLName");
		String usersName = usersFName + usersLName;
		
		if(checked == null && conn != null)
		{
			String cName = null;
			Cookie[] cookies = request.getCookies();
		       if (cookies != null) 
		       {
		           for (Cookie cookie : cookies)
		           {
		               if (usersName.equals(cookie.getName())) {
		                   cName = cookie.getValue();
		               }
		           }
		       }
		       
		   try
		   {
			   List list = new ArrayList();
			   String sqlQuery = "Select * from customers where customers.first_name = '" + usersFName + "' and customers.last_name ='" +"'";
			   PreparedStatement pstm = conn.prepareStatement(sqlQuery);
			   ResultSet rs = pstm.executeQuery();
			   List userInfo = null;
			   
			   while(rs.next())
			   {
				   	userInfo = new ArrayList();
				   	userInfo.add(rs.getString(1));
				   	userInfo.add(rs.getString(2));
					list.add(userInfo);
			   }
			   
			   session.setAttribute("loginedU", userInfo);;
				   /*
				    * public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
 
       // On the JSP can access ${loginedUser}
       session.setAttribute("loginedUser", loginedUser);
   }
				    */
				   
			   }
		   		catch(SQLException e)
		   {
		   			e.printStackTrace();
		   			
		   }
			session.setAttribute("Cookie_Checked", "Checked");;
		}
		
		chain.doFilter(request, response);
		/*
		 * public static void storeConnection(ServletRequest request, Connection conn) {
		       request.setAttribute(ATT_NAME_CONNECTION, conn);
		   }
		 
		   // Get the Connection object has been stored in one attribute of the request.
		   public static Connection getStoredConnection(ServletRequest request) {
		       Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		       return conn;
		   }
		 */
		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
