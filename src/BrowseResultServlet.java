

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BrowseResultServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		List list = new ArrayList();
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "movie_db";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "lilwizzard1";
		
		String browseType = request.getParameter("browseBy");
		String sqlQuery = null;
		if(browseType.equals("title"))
			sqlQuery = "SELECT title, id  FROM movies";
		else if(browseType.equals("genre"))
			sqlQuery = "SELECT name, id  FROM genres";
		
		try {
			Class.forName(driver);
			Connection connection;
			connection = DriverManager.getConnection(url+db, user, password);
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				List movies = new ArrayList();
				movies.add(rs.getString(1));
				movies.add(rs.getString(2));
				list.add(movies);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("browseResult", list);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/browseResult.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}