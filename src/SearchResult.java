

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

public class SearchResult extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		List list = new ArrayList();
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "e951l632v";
		
		String browseType = request.getParameter("stars");
		
		String sqlQuery = "SELECT first_name, last_name  FROM stars";
		
		try {
			Class.forName(driver);
			Connection connection;
			connection = DriverManager.getConnection(url+db, user, password);
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				list.add(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("hu");
		request.setAttribute("browseResult", list);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/searchresult.jsp");
		dispatcher.forward(request, response);
		
	}

}
