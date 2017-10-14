

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
		
		String adjQuantity = request.getParameter("adjQuantity");
		String name = request.getParameter("adjMovieName");
		
		if(adjQuantity != null)
		{
			try
			{
				name = request.getParameter("adjMovieName");
				Class.forName(driver);
				conn = DriverManager.getConnection(url+db, user, password);
				st = conn.createStatement();
				
				String sqlQuery = "select id from movies where movies.title = '" + name + "';";
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
				String sqlQuery2 = "select title from movies where movies.id = '" + movieID + "';";
				rs2 = st.executeQuery(sqlQuery2);
				rs2.next();
				name = rs2.getString(1);
				
				int q = Integer.parseInt(adjQuantity);
				PreparedStatement ps = (PreparedStatement) conn.prepareStatement("Update shoppingcart SET quantity =  '" + q + "' where customerid = '" + userID + "' and movieid = '" + movieID + "';");
				ps.execute();
				Movies movie = new Movies();
				movie.setQuantity(q);
				movie.setTitle(name);
				movie.setPrice((float) 1.99);
				cart.addToCart(name, movie);
				session.setAttribute("cart", cart);
				
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
			request.setAttribute("adjQuantity", name);
		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/cart");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
