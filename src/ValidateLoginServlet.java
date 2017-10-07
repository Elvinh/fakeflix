

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		//CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		java.net.CookieManager cm = new java.net.CookieManager();
		java.net.CookieHandler.setDefault(cm);

	    Connection conn = null;
		PreparedStatement login = null;
		PreparedStatement getShoppingCart = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = "";
		String lastName = "";
		int userID = -1;
		try
		{
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
			String sqlQuery = "Select first_name, last_name, id from customers where customers.email=? and customers.password=?";
		    login = conn.prepareStatement(sqlQuery);
		    login.setString(1, email);
		    login.setString(2, password);
		    rs = login.executeQuery();
		    if(rs.next())
		    {
			    ShoppingCart cart = new ShoppingCart();

			    firstName = rs.getString(1);
			    lastName = rs.getString(2);
			    userID = rs.getInt(3);
			    
				sqlQuery = "select movies.title, movies.price, shoppingcart.quantity from movies "
						+ "inner join shoppingcart "
						+ "on movies.id = shoppingcart.movieID and shoppingCart.customerID = ?";
				getShoppingCart = conn.prepareStatement(sqlQuery);
				getShoppingCart.setInt(1, userID);
				rs2 = getShoppingCart.executeQuery();
				
				while(rs2.next()) {
			    	Movies movie = new Movies();
			    	movie.setTitle(rs2.getString(1));
			    	movie.setPrice(rs2.getFloat(2));
			    	movie.setQuantity(rs2.getInt(3));
					cart.addToCart(movie.getTitle(), movie);
				}
				
				session.setAttribute("cart", cart);
				
			    Cookie currentUser = new Cookie("loginedUser", "true");
			    Cookie myCookieF = new Cookie("first_name", firstName);
			    Cookie myCookieL = new Cookie("last_name", lastName);
			    Cookie myCookieID = new Cookie("id", String.valueOf(userID));
		    	
			    myCookieF.setMaxAge(60 * 5);
		    	myCookieL.setMaxAge(60 * 5);
		    	myCookieID.setMaxAge(60 * 5);
		    	currentUser.setMaxAge(60 * 5);
		    	
			    response.addCookie(currentUser);
			    response.addCookie(myCookieF);
			    response.addCookie(myCookieL);
			    response.addCookie(myCookieID);
		    	/*
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
		    	}*/
		    	
			    
			    //For chocolate chip cookies
			   // session = request.getSession();
			    //session.setAttribute("loginedU", (String) nameList.get(0) + " " + nameList.get(1));


		    	//request.setAttribute("email", email);
			    //request.setAttribute("nameList", nameList);
			    response.sendRedirect(request.getContextPath() + "/home");
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
			DbUtils.closeQuietly(conn);
		}

	}

}
