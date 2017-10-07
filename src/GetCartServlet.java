

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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
 * Servlet implementation class GetCartServlet
 */
@WebServlet("/cart/*")
public class GetCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCartServlet() {
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
		cart = (ShoppingCart) session.getAttribute("cart");

		if(cart == null) {
			cart = new ShoppingCart();
			Cookie[] cookies = request.getCookies();
			for(Cookie cook : cookies) {
				if(cook.getName().equals("id")) {

					String url = "jdbc:mysql://localhost:3306/";
					String db = "moviedb";
					String driver = "com.mysql.jdbc.Driver";
					String user = "root";
					String password = "admin";
					
					Connection conn = null;
					Statement st = null;
					ResultSet rs = null;
					
					String sqlQuery = "select movies.title, movies.price, shoppingcart.quantity from movies inner join shoppingcart on movies.id = shoppingcart.movieID and shoppingCart.customerID = " + cook.getValue() + "";
					try {
						Class.forName(driver);
						conn = DriverManager.getConnection(url+db, user, password);
						st = conn.createStatement();
						rs = st.executeQuery(sqlQuery);
						while(rs.next()) {
					    	Movies movie = new Movies();
					    	movie.setTitle(rs.getString(1));
					    	movie.setPrice(rs.getFloat(2));
					    	movie.setQuantity(rs.getInt(3));
							cart.addToCart(movie.getTitle(), movie);
						}
					} catch (SQLException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						DbUtils.closeQuietly(rs);
						DbUtils.closeQuietly(st);
						DbUtils.closeQuietly(conn);
					}
					session.setAttribute("cart", cart);
				}
			}
		}
		HashMap<String, Movies> items = cart.getCart();
		
		request.setAttribute("shoppingCart", items);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/shoppingCart.jsp");
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
