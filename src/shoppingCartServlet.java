

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
<<<<<<< HEAD
		String removeMovie = request.getParameter("removeMovie");
		String movieName = request.getParameter("addMovie");
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
				cart.remove(removeMovie, movie);
				cart.print();
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
				DbUtils.closeQuietly(st);
				DbUtils.closeQuietly(conn);
			}
=======
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
			Movies movie = new Movies();
			movie.setQuantity(1);
			movie.setTitle(movieName);
			movie.setPrice((float) 1.99);
			cart.addToCart(movieName, movie);
			session.setAttribute("cart", cart);
			//response.addCookie(c1);
		}
>>>>>>> 2490d3cf22945c4a17ffc907e6831faf27ce72a1
	    
		    request.setAttribute("addedItem", movieName);
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addedToCart.jsp");
			dispatcher.forward(request, response);
		}
	}
<<<<<<< HEAD
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
=======
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
			
>>>>>>> 2490d3cf22945c4a17ffc907e6831faf27ce72a1
		
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
