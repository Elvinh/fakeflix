

import java.io.IOException;
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

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class shoppingCartServlet
 */
@WebServlet("/shoppingCart/*")
public class shoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		ShoppingCart cart;
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
	
		response.setContentType("text/html");
		
		cart = (ShoppingCart) session.getAttribute("cart");
		
		if(cart == null)
		{
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}

		String removeMovie = request.getParameter("removeMovie");
		String movieName = request.getParameter("addMovie");
		System.out.println("MOVIE NAME: " + movieName);
		System.out.println("REMOVE MOVIE NAME: " + removeMovie);
		
		if(removeMovie != null)
		{
			try
			{
				Class.forName(driver);
				conn = DriverManager.getConnection(url+db, user, password);
				st = conn.createStatement();
				
				String sqlQuery = "select id from movies where movies.title = '" + removeMovie + "';";
				rs = st.executeQuery(sqlQuery);
				int movieID = 0;
				if(rs.next())
				{
					System.out.println("I'm getting movieID");
					movieID = rs.getInt(1);
					System.out.println("I'm getting movieID here");
				}
				String userID = "";
				Cookie [] cook = request.getCookies();

				for(Cookie cookies : cook)
				{
					if(cookies.getName().equals("id"))
					{
						userID = cookies.getValue();
					}
				}
				String sqlQuery3 = "select quantity from shoppingcart where customerid = '" + userID + "' and movieid = '" + movieID + "'";
				rs3 = st.executeQuery(sqlQuery3);
					
				rs3.next();
				int quantity = rs3.getInt(1);
				System.out.println("Movie I want to delete ID "  + movieID);
				System.out.println("UserID that I'm deleting from "  + userID);
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement("delete from shoppingcart where customerid = '" + userID + "' and movieid = '" +movieID +"';");
				ps.execute();
			
				Movies movie = new Movies();
				movie.setQuantity(quantity);
				movie.setTitle(removeMovie);
				movie.setPrice((float) 1.99);
				cart.removeFromCart(removeMovie, movie);
				session.setAttribute("cart", cart);
			
			}
			catch(SQLException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally
			{
				DbUtils.closeQuietly(rs);
				DbUtils.closeQuietly(rs2);
				DbUtils.closeQuietly(rs3);
				DbUtils.closeQuietly(st);
				DbUtils.closeQuietly(conn);
			}
			
			 request.setAttribute("removedMovie", removeMovie);
			 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/removedFromCart.jsp");
			 dispatcher.forward(request, response);
		}
		else if(movieName != null)
		{
			try
			{
				Class.forName(driver);
				conn = DriverManager.getConnection(url+db, user, password);
				st = conn.createStatement();
				
				Movies movie = new Movies();
						
				String sqlQuery = "select id from movies where movies.title = '" + movieName + "';";
				rs = st.executeQuery(sqlQuery);
				int movieID = 0;
					
				rs.next();
				movieID = rs.getInt(1);
					
				String userID = "";
				Cookie [] cook = request.getCookies();
		
				for(Cookie cookies : cook)
				{
					if(cookies.getName().equals("id"))
					{
						userID = cookies.getValue();
					}
				}
					
				String sqlQuery2 = "select customerid, movieid from shoppingcart where customerid = '" + userID + "' and movieid = '" + movieID + "'";
				rs2 = st.executeQuery(sqlQuery2);
					
				if(rs2.absolute(1))
				{
					System.out.println("I'm here");
					String sqlQuery3 = "select quantity from shoppingcart where customerid = '" + userID + "' and movieid = '" + movieID + "'";
					rs3 = st.executeQuery(sqlQuery3);
					rs3.next();
					int quantity = rs3.getInt(1) + 1;
					movie = new Movies();
					movie.setTitle(movieName);
					movie.setQuantity(quantity);
					movie.setPrice((float) 1.99);
					PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Update shoppingcart SET quantity =  '" + quantity + "' where customerid = '" + userID + "' and movieid = '" + movieID + "';");
					ps.execute();
					cart.addToCart(movieName, movie);
					session.setAttribute("cart", cart);
				}
				else
				{
					movie = new Movies();
					movie.setQuantity(1);
					movie.setTitle(movieName);
					movie.setPrice((float) 1.99);
					cart.addToCart(movieName, movie);
					PreparedStatement ps = (PreparedStatement) conn.prepareStatement("insert into shoppingcart values ( " + userID + ", " + movieID + ", " + 1 +  ")");
					ps.execute();
					session.setAttribute("cart", cart);
				}				
			}
			catch(SQLException | ClassNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			finally 
				{
					DbUtils.closeQuietly(rs);
					DbUtils.closeQuietly(rs2);
					DbUtils.closeQuietly(rs3);
					DbUtils.closeQuietly(conn);
				}
	    
		    request.setAttribute("addedItem", movieName);
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addedToCart.jsp");
			dispatcher.forward(request, response);
		    
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
