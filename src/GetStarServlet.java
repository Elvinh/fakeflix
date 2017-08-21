

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

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class getStarServlet
 */
@WebServlet("/getStar/*")
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
		List moviesByStar = new ArrayList();
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String star = request.getParameter("selected");
		//String firstName = star.split(" ")[0];
		//String lastName = star.split(" ")[1];
		String sqlQuery = "SELECT * FROM moviedb.stars WHERE stars.id = " + star;
		String sqlQuery2 = "SELECT title, banner_url FROM movies WHERE movies.id IN (SELECT movie_id FROM stars_in_movies WHERE stars_in_movies.star_id = " + star + ")";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url+db, user, password);
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				String fullName = rs.getString(2) + " " + rs.getString(3);
				starAttributes.add(rs.getString(1));
				starAttributes.add(fullName);
				starAttributes.add(rs.getString(4));
				starAttributes.add(rs.getString(5));
			}
			rs = st.executeQuery(sqlQuery2);
			while(rs.next()) {
				List movieAttributes = new ArrayList();
				movieAttributes.add(rs.getString(1));
				movieAttributes.add(rs.getString(2));
				moviesByStar.add(movieAttributes);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
		}
		
		request.setAttribute("requestedStar", starAttributes);
		request.setAttribute("moviesByStar", moviesByStar);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getStarView.jsp");
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
