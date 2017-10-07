

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.DbUtils;


/**
 * Servlet implementation class ValidateLoginServlet
 */
@WebServlet("/user/*")

public class ValidateLoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		//CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		java.net.CookieManager cm = new java.net.CookieManager();
		java.net.CookieHandler.setDefault(cm);
	    ShoppingCart sc = new ShoppingCart();

	    Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		List nameList = new ArrayList();
		String email = request.getParameter("email");
		System.out.println(email);
		String password = request.getParameter("password");
		System.out.println(password);
		int userID = -1;
		try
		{
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
			st = conn.createStatement();
		    rs = st.executeQuery("Select *  from customers where customers.email = '"+ email + "' and customers.password = '" + password + "'");

		    if(rs.absolute(1))
		    {
		    	
		    	rs2 = st.executeQuery("Select first_name, last_name, id from customers where customers.email ='"+ email + "' and customers.password = '" + password + "'");
			    while(rs2.next())
			    {
			    	nameList.add(rs2.getString(1));
			    	nameList.add(rs2.getString(2));
			    	userID = rs2.getInt(3);
			    }
			    
			    Cookie currentUser = new Cookie("loginedUser", (String) "true");
			    Cookie myCookieF = new Cookie("first_name", (String) nameList.get(0));
			    Cookie myCookieL = new Cookie("last_name", (String) nameList.get(1));
			    Cookie myCookieID = new Cookie("id", String.valueOf(userID));
		    	
			    myCookieF.setMaxAge(60 * 5);
		    	myCookieL.setMaxAge(60 * 5);
		    	myCookieID.setMaxAge(60 * 5);
		    	currentUser.setMaxAge(60 * 5);
		    	
		    	Cookie[] cookies = request.getCookies();
		    	
		    	if(cookies == null)
		    	{
			    	response.addCookie(currentUser);
			    	response.addCookie(myCookieF);
			    	response.addCookie(myCookieL);
			    	response.addCookie(myCookieID);
		    	
		    	}
		    	else
		    	{
		    		//There is some movie cookie that has been added.
		    		//Need to shift every cookie over.
		    		//***working on this part***
		    		Cookie [] myCurrentCookies = request.getCookies();
		    	
		    		int orgCookieLength = myCurrentCookies.length;
		    		
		    		for(int i = 0; i < orgCookieLength; i++)
		    		{
		    			Cookie cookie = myCurrentCookies[i];
		    			
		    			cookie.setMaxAge(0);
		    			response.addCookie(cookie);
		    			
		    		}
		    		
		    		myCookieF.setMaxAge(-1);
			    	myCookieL.setMaxAge(-1);
			    	currentUser.setMaxAge(-1);
			    	myCookieID.setMaxAge(-1);

			    	
		    		response.addCookie(currentUser);
		    		response.addCookie(myCookieF);
		    		response.addCookie(myCookieL);
			    	response.addCookie(myCookieID);
		    		
		    		for(int i = orgCookieLength-1; i >= 0; --i)
		    		{
		    			System.out.println(myCurrentCookies.length);
		    			System.out.println("I'M HERE");
		    			System.out.println(myCurrentCookies[i].getName());
		    			System.out.println(myCurrentCookies[i].getValue());

		    			response.addCookie((myCurrentCookies[i]));
		    		}
		    	}
		    	
			    
			    //For chocolate chip cookies
			   // session = request.getSession();
			    //session.setAttribute("loginedU", (String) nameList.get(0) + " " + nameList.get(1));


		    	request.setAttribute("email", email);
			    request.setAttribute("nameList", nameList);
			    response.sendRedirect(request.getContextPath() + "/userInfo.jsp");
				//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userInfo.jsp");
				//dispatcher.forward(request, response);

		    }
		    else
		    {
		    	request.setAttribute("badEmail", email);
		    	request.setAttribute("badPW", password);
		    	
		    	response.sendRedirect(request.getContextPath() + "/loginForm.jsp");

		    }
		  
		}
		 catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(rs2);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
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
