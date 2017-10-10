

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
				movie.setQuantity(1);
				movie.setTitle(movieName);
				movie.setPrice((float) 1.99);
				cart.addToCart(movieName, movie);
				session.setAttribute("cart", cart);
				System.out.print("Hello");
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
				
				
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement("insert into shoppingcart values ( " + userID + ", " + movieID + ", " + 1 +  ")");
				ps.execute();
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
				DbUtils.closeQuietly(st);
				DbUtils.closeQuietly(conn);
			}
			
		
		
			
			
			//response.addCookie(c1);
		
	    
	    request.setAttribute("addedItem", movieName);
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addedToCart.jsp");
		dispatcher.forward(request, response);
	}
	/*
	 * <%
		
		Cookie[] cookies =  request.getCookies();
		String name = "";
		int quantity = 0;
		int counter = 0;
		String tempName = "";
		String tempValue = "";
	
		// 1. SC works so far with user logging in and adding movies to cart. 
		// 1. Correctly takes out the entire list when user logs out
		// 2. When anon user add movies to list, correctly adds them in
		// 2. And leaves them in until cookie expires.
		// SITUATION 3. NEEDS WORK.
		// 3. Fix situation where user adds in movies to SC, but then logins.
		// Currently the indexing is messed up. The cookies list is out of order, will either
		// A. Need to find a formula to fix the indexing
		// B. When user logs in, place info in the correct slots, then add back the movies to the list.
		
		if(cookies != null)
		{
			for(int i = 0; i < cookies.length; i++)
			{
				System.out.println("Length of Cookies @ SC: " + cookies.length);
				tempName = cookies[i].getName();
				tempValue = cookies[i].getValue();
				
				%><p><%= tempName %></p>
				<p><%= tempValue %></p><%
			}
			if(cookies[0].getName().equals("loginedUser"))
			{
				counter = 4;
			}
			else if (cookies[1].getName().equals("loginedUser"))
			{
				counter = 4;
			}
			else
			{
				counter = 0;	
			}
			for(int i = counter; i < cookies.length; i++)
			{
					//name = cookies[i].getName();
					//quantity = Integer.parseInt(cookies[i].getValue());
					%>
					
					<tr>
						<td>
							<p> <%= name %></p>
						</td>
						<td>
							<p> <%= quantity %></p>
						</td>
						<td>
							<p> 1</p>
						</td>
					</tr><% 
			}
		}
			
		
		%>
		
		
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
