

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		//CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		java.net.CookieManager cm = new java.net.CookieManager();
		java.net.CookieHandler.setDefault(cm);
		HashMap<String, Movies> items = new HashMap<>();
	    ShoppingCart sc = new ShoppingCart();

	    Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;


		List nameList = new ArrayList();
		String email = request.getParameter("email");
		System.out.println(email);
		String password = request.getParameter("password");
		System.out.println(password);
		String userID = "";
		try
		{
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
			st = conn.createStatement();
		    rs = st.executeQuery("Select *  from customers where customers.email = '"+ email + "' and customers.password = '" + password + "'");

		    if(rs.absolute(1))
		    {
		    	session = request.getSession();
		    	
		    	rs2 = st.executeQuery("Select first_name, last_name, id from customers where customers.email ='"+ email + "' and customers.password = '" + password + "'");
			    while(rs2.next())
			    {
			    	nameList.add(rs2.getString(1));
			    	nameList.add(rs2.getString(2));
			    	userID = rs2.getString(3);

			    }
			    rs3 = st.executeQuery("select movies.title, movies.price, shoppingcart.quantity from movies inner join shoppingcart on movies.id = shoppingcart.movieID and shoppingCart.customerID = " + userID + "");
			    while(rs3.next()) 
			    {
			    	Movies movie = new Movies();
			    	movie.setTitle(rs3.getString(1));
			    	movie.setPrice(rs3.getFloat(2));
			    	movie.setQuantity(rs3.getInt(3));
					sc.addToCart(movie.getTitle(), movie);

				}
			    System.out.println(sc.getCart().get("Eurotrip"));
			    items = sc.getCart();

			    Cookie currentUser = new Cookie("loginedUser", (String) "true");
			    Cookie myCookieF = new Cookie("first_name", (String) nameList.get(0));
			    Cookie myCookieL = new Cookie("last_name", (String) nameList.get(1));
		    	myCookieF.setMaxAge(60 * 5);
		    	myCookieL.setMaxAge(60 * 5);
		    	currentUser.setMaxAge(60*5);
		    	
		    	Cookie[] cookies = request.getCookies();
		    	
		    	if(cookies == null)
		    	{
			    	response.addCookie(currentUser);
			    	response.addCookie(myCookieF);
			    	response.addCookie(myCookieL);
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
			    	
		    		response.addCookie(currentUser);
		    		response.addCookie(myCookieF);
		    		response.addCookie(myCookieL);
		    		
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
			    session.setAttribute("cart", sc);

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
			DbUtils.closeQuietly(rs3);
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
