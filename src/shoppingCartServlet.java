

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
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		ShoppingCart cart;
		
		cart = (ShoppingCart) session.getAttribute("cart");
		
		if(cart == null)
		{
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		/*
    	Cookie[] cookies = request.getCookies();
    	for(int i = 0; i < cookies.length; i++)
    	{
    		if(cookies[i].getName().equals("loginedUser"))
    		{
    			//pass cart along from anon user to login user
    			cart = (ShoppingCart) session.getAttribute("cart");
    			HashMap<String, Integer> items = cart.getCart();
    			
    			for(int j = 0; i < items.size(); j++)
    			{
    				try
    				{
	    				Class.forName("com.mysql.jdbc.Driver");
				    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
						st = conn.createStatement();
					    rs = st.executeQuery("INSERT INTO shoppingCart VALUES("+ ); 
    				}
    				catch(SQLException | ClassNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} finally {
    					DbUtils.closeQuietly(rs);
    					DbUtils.closeQuietly(st);
    					DbUtils.closeQuietly(conn);
    				}
    				
    			}
    			 try
    			    {
    			    	Class.forName("com.mysql.jdbc.Driver");
    			    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
    					st = conn.createStatement();
    				    rs = st.executeQuery("select name from genres"); 		
    				    
    				    if(rs.absolute(1))
    				    {
    				    	while(rs.next())
    				    	{
    				    		list.add(rs.getString(1));
    				    		/*
    				    		 * 	movies.add(rs.getString(1));
    						movies.add(rs.getString(2));
    						list.add(movies);
    				    		 
    				    	}
    				    }
    			    }
    			    catch(SQLException | ClassNotFoundException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				} finally {
    					DbUtils.closeQuietly(rs);
    					DbUtils.closeQuietly(st);
    					DbUtils.closeQuietly(conn);
    				}
    				
    			
    		}
    	}
*/

		String movieName = request.getParameter("addMovie");
		System.out.println("MOVIE NAME: " + movieName);
		
		if(movieName != null)
		{
			cart.addToCart(movieName, 19);
			session.setAttribute("cart", cart);
			//response.addCookie(c1);
		}
	    
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
		
		<tr>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<a href = "shoppingCart?"selected=<c:out value = "${movieList[0]}"/>">
						<c:out value = "${movieList[0]}"/>
					</a>
				</c:forEach>
			</td>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<p><c:out value = "${movieList[1]}"/><p>
				</c:forEach>
			</td>
			<td>
				<c:forEach var = "movieList" items = "${addedMovies}">
					<input type = "text" placeholder = "1" name = quantity" required>
				</c:forEach>
			</td>
		</tr>
	
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
