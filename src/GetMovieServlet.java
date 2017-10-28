

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
 * Servlet implementation class MoviesByStar
 */
@WebServlet("/getMovie/*")
public class GetMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMovieServlet() {
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
		List list = new ArrayList();
		List stars = new ArrayList();
		Movies movie = null;
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String selectedType = request.getParameter("selected");
		String movieId = request.getParameter("id");
		String type = null;
		String sqlQuery = null;
		String isLiked = "false";
		
		selectedType = "%" + selectedType + "%";
		sqlQuery = "SELECT * FROM movies WHERE movies.title LIKE \"" + selectedType + "\"";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url+db, user, password);
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
		
			
			if(rs.absolute(1)) {
				rs = st.executeQuery(sqlQuery);
				while(rs.next()) {
					 movie = new Movies( rs.getInt(1),
											  rs.getString(2),
											  rs.getInt(3),
											  rs.getString(4),
											  rs.getString(5),
											  rs.getString(6),
											  rs.getFloat(7));
				}
				type = "title";
				sqlQuery = "SELECT first_name, last_name, id FROM stars WHERE stars.id in (SELECT star_id FROM stars_in_movies WHERE stars_in_movies.movie_id in (SELECT id FROM movies WHERE movies.title LIKE \"" + selectedType + "\"))";
				rs = st.executeQuery(sqlQuery);
				while(rs.next()) {
					List star = new ArrayList();
					String fullName = rs.getString(1) + " " + rs.getString(2);
					star.add(fullName);
					star.add(rs.getString(3));
					stars.add(star);
				}
				rs.close();
			}
			else {
				sqlQuery = "SELECT title FROM movies WHERE movies.id in (SELECT movie_id FROM genres_in_movies WHERE genres_in_movies.genre_id in (SELECT id FROM genres WHERE genres.name = \"" + selectedType + "\"))";
				ResultSet rs2 = st.executeQuery(sqlQuery);

				while (rs2.next())
		        {
					list.add(rs2.getString(1));
		        }
				type = "genre";
				rs2.close();
			}
			Cookie test = null;
			Cookie[] cookies = request.getCookies();
			for(Cookie cook : cookies) {
				if(cook.getName().equals("id")) {
					test = cook;
					sqlQuery = "SELECT * FROM Likes WHERE customer_id = '" + cook.getValue() + "' AND movie_id = '" + movieId + "'";
					rs = st.executeQuery(sqlQuery);
					if(rs.absolute(1)) {
						isLiked = "true";
					}
				}
			}
			if(test == null)
				isLiked = "true";

					
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
		}
		
		request.setAttribute("requestedMovie", movie);
		request.setAttribute("type", type);
		request.setAttribute("stars", stars);
		request.setAttribute("isLiked", isLiked);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getMovieView.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
