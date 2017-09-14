

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		ShoppingCart cart;
		
		cart = (ShoppingCart) session.getAttribute("cart");
		
		if(cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}

		String movieName = request.getParameter("addMovie");
		System.out.println("MOVIE NAME: " + movieName);
		
		if(movieName != null)
		{
			cart.addToCart(movieName, 19);
			session.setAttribute("cart", cart);
			//Cookie c1 = new Cookie(movieName, "1");
			//c1.setMaxAge(60*3);
			//response.addCookie(c1);
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
		doGet(request, response);
	}

}
