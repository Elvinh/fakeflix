

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
	
		
		cart = (ShoppingCart) session.getAttribute("cart");
		
		if(cart == null)
		{
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		
		String movieName = request.getParameter("addMovie");
		System.out.println("MOVIE NAME: " + movieName);
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url+db, user, password);
			st = conn.createStatement();
			
			
			if(movieName != null)
			{
				Movies movie = new Movies();
				/*movie.setQuantity(1);
				movie.setTitle(movieName);
				movie.setPrice((float) 1.99);
				cart.addToCart(movieName, movie);
				session.setAttribute("cart", cart);
				*/
				String sqlQuery = "select id from movies where movies.title = '" + movieName + "';";
				rs = st.executeQuery(sqlQuery);
				int movieID = 0;
				if(rs.next())
				{
					movieID = rs.getInt(1);
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
				
				String sqlQuery2 = "select customerid, movieid from shoppingcart where customerid = '" + userID + "' and movieid = '" + movieID + "'";
				rs2 = st.executeQuery(sqlQuery2);
			
				if(rs2.absolute(1))
				{
					System.out.println("I'm here");
					String sqlQuery3 = "select quantity from shoppingcart where customerid = '" + userID + "' and movieid = '" + movieID + "'";
					rs3 = st.executeQuery(sqlQuery3);
					
					rs3.next();
					int quantity = rs3.getInt(1) + 1;
					System.out.println(quantity);
					PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Update shoppingcart SET quantity =  '" + quantity + "' where customerid = '" + userID + "' and movieid = '" + movieID + "';");
					ps.execute();
					movie = new Movies();
					movie.setQuantity(quantity);
					movie.setTitle(movieName);
					movie.setPrice((float) 1.99);
					cart.addToCart(movieName, movie);
					session.setAttribute("cart", cart);

				}
				else
				{
					System.out.println("I'm over here");
					PreparedStatement ps = (PreparedStatement) conn.prepareStatement("insert into shoppingcart values ( " + userID + ", " + movieID + ", " + 1 +  ")");
					ps.execute();
					movie = new Movies();
					movie.setQuantity(1);
					movie.setTitle(movieName);
					movie.setPrice((float) 1.99);
					cart.addToCart(movieName, movie);
					session.setAttribute("cart", cart);
				}
				//PreparedStatement stmt=con.prepareStatement("insert into Emp values(?,?)"); 
				
				
				
		}
		}
		catch(SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		finally {
				DbUtils.closeQuietly(rs);
				DbUtils.closeQuietly(rs2);
				DbUtils.closeQuietly(rs3);
				DbUtils.closeQuietly(st);
				DbUtils.closeQuietly(conn);
			}
	    
	    request.setAttribute("addedItem", movieName);
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addedToCart.jsp");
		dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		ShoppingCart cart;
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
		
		String movieName = request.getParameter("removeMovie");
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url+db, user, password);
			st = conn.createStatement();
			
			if(movieName != null)
			{
				rs = st.executeQuery("delete from shoppingcart where in movieid = (select id from movies.id = '" + movieName + "')");
			}
		}
		catch(SQLException | ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
		}
		
		//doGet(request, response);
	}

}
