

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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class getStarServlet
 */
@WebServlet("/getStarServlet")
public class GetStarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStarServlet() {
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
		List starAttributes = new ArrayList();
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "movie_db";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "lilwizzard1";
		
		String star = request.getParameter("selected");
		String firstName = star.split(" ")[0];
		String lastName = star.split(" ")[1];
		String sqlQuery = "SELECT * FROM moviedb.stars WHERE stars.first_name = '" + firstName + "' AND stars.last_name = '" + lastName + "'";
		
		try {
			Class.forName(driver);
			Connection connection;
			connection = DriverManager.getConnection(url+db, user, password);
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				
				starAttributes.add(rs.getString(1));
				starAttributes.add(rs.getString(2));
				starAttributes.add(rs.getString(3));
				starAttributes.add(rs.getString(4));
				starAttributes.add(rs.getString(5));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("requestedStar", starAttributes);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getStar.jsp");
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
