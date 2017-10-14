

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
@WebServlet("/auth/*")

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

		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String sqlPassword = "admin";
		
	    Connection conn = null;
		PreparedStatement login = null;
		PreparedStatement getShoppingCart = null;
	    Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = "";
		String lastName = "";
		int userID = -1;
		
		ShoppingCart cart = null;
		boolean loginSuccess = true;
		
		try
		{
	    	Class.forName(driver);
			conn = DriverManager.getConnection(url+db, user, sqlPassword);
			
	    	String sqlQuery = "Select first_name, last_name, id from customers where customers.email=? and customers.password=?";
		    login = conn.prepareStatement(sqlQuery);
		    login.setString(1, email);
		    login.setString(2, password);
		    rs = login.executeQuery();
			

		    
			st = conn.createStatement();
		    
		    if(rs.next())
		    {
		    	/* Set cookies for user that logged in */
			    firstName = rs.getString(1);
			    lastName = rs.getString(2);
			    userID = rs.getInt(3);
			    
			    session.setAttribute("user", userID);
			    
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
			    /* ****************** */
			    
		    	cart = (ShoppingCart) session.getAttribute("cart");
		    	if(cart != null)
		    	{
			    	if(!cart.isEmpty())
			    	{
			    		sqlQuery = "select movies.title, movies.price, shoppingcart.quantity from movies "
								+ "inner join shoppingcart "
								+ "on movies.id = shoppingcart.movieID and shoppingcart.customerID = '1'";
			    		rs3 = st.executeQuery(sqlQuery);
			    	}
		    	}
		    	

			    cart = new ShoppingCart();

				sqlQuery = "Select movies.title, movies.price, shoppingcart.quantity from movies inner join shoppingcart on movies.id = shoppingcart.movieID and shoppingcart.customerID = ?";
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
				
				if(rs3 != null) {
					while(rs3.next())
					{
						Movies movie = new Movies();
						movie.setTitle(rs3.getString(1));
						movie.setPrice(rs3.getFloat(2));
						movie.setQuantity(rs3.getInt(3));
						cart.addToCart(movie.getTitle(), movie);
					}
				}
				session.setAttribute("cart", cart);
				
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Update shoppingcart SET customerid =  '" + userID + "' where customerid = '1';");
				ps.execute();
				
		    }
		    else
		    {
		    	request.setAttribute("badEmail", email);
		    	request.setAttribute("badPW", password);
		    	loginSuccess = false;
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
			DbUtils.closeQuietly(login);
			DbUtils.closeQuietly(getShoppingCart);
			DbUtils.closeQuietly(conn);
		}
		
		if(loginSuccess) {
			response.sendRedirect(request.getContextPath() + "/home");
		}
		else
			response.sendRedirect(request.getContextPath() + "/loginForm.jsp");
	    
	}

}
