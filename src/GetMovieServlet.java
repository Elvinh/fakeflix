

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
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "movie_db";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "lilwizzard1";
		
		String selectedType = request.getParameter("selected");
		String type = null;
		String sqlQuery = null;
		
		
		sqlQuery = "SELECT title, year, director, banner_url, trailer_url FROM movies WHERE movies.title = \"" + selectedType + "\"";
		
		try {
			Class.forName(driver);
			Connection connection;
			connection = DriverManager.getConnection(url+db, user, password);
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			
			if(rs.absolute(1)) {
				rs = st.executeQuery(sqlQuery);
				while(rs.next()) {
					List movie = new ArrayList();
					movie.add(rs.getString(1));
					movie.add(rs.getString(2));
					movie.add(rs.getString(3));
					movie.add(rs.getString(4));
					movie.add(rs.getString(5));
					list.add(movie);
				}
				type = "title";
				sqlQuery = "SELECT first_name, last_name, id FROM stars WHERE stars.id in (SELECT star_id FROM stars_in_movies WHERE stars_in_movies.movie_id in (SELECT id FROM movies WHERE movies.title = \"" + selectedType + "\"))";
				rs = st.executeQuery(sqlQuery);
				while(rs.next()) {
					List star = new ArrayList();
					star.add(rs.getString(1));
					star.add(rs.getString(2));
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
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("requestedMovie", list);
		request.setAttribute("type", type);
		request.setAttribute("stars", stars);
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
