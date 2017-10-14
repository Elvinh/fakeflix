

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveFromCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		ShoppingCart cart;
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		cart = (ShoppingCart) session.getAttribute("cart");
		String removeMovie = request.getParameter("removeMovie");

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
			
			if(userID == "")
			{
				userID = "1";
			}
			String sqlQuery3 = "select quantity from shoppingcart where customerid = '" + userID + "' and movieid = '" + movieID + "'";
			rs3 = st.executeQuery(sqlQuery3);
				
			rs3.next();
			int quantity = rs3.getInt(1);
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
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart");
		 dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
