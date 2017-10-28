

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/home/*")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		List genres = new ArrayList();
		List stars = new ArrayList();
		List<List<Movies>> genreMovies = new ArrayList<List<Movies>>();
		List<List<Movies>> starMovies = new ArrayList<List<Movies>>();
		List<Movies> mostLikedMovies = new ArrayList<>();
		
		// Get x random genres
		String sqlQuery = "SELECT name FROM genres ORDER by RAND() LIMIT 4";
		String sqlQuery2 = "SELECT id, first_name, last_name FROM stars ORDER by RAND() LIMIT 2";
		String sqlQuery3 = "select * from movies inner join (select movie_id from likes group by movie_id order by count(*) desc limit 7) as most_liked on movies.id = most_liked.movie_id";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url+db, user, password);
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
			
			while(rs.next()) {
				genres.add(rs.getString(1));
				
			}
			
			for(int i = 0;i < genres.size();i++) {
				List<Movies> moviesFromAGenre = new ArrayList<Movies>();
				
				// Get movies from a certain genre
				sqlQuery = "SELECT * FROM movies WHERE movies.id in "
						+ "(SELECT movie_id FROM genres_in_movies WHERE genres_in_movies.genre_id in "
						+ "(SELECT id FROM genres WHERE genres.name = \"" + genres.get(i) + "\")) "
						+ "limit 10";
				
				rs = st.executeQuery(sqlQuery);
				while(rs.next()) {
					Movies movie = new Movies( rs.getInt(1),
							  rs.getString(2),
							  rs.getInt(3),
							  rs.getString(4),
							  rs.getString(5),
							  rs.getString(6),
							  rs.getFloat(7));
					
					moviesFromAGenre.add(movie);
				}
				genreMovies.add(moviesFromAGenre);
			}
			
			
			// Get movies from a certain star
			rs = st.executeQuery(sqlQuery2);
			
			while(rs.next()) {
				Stars star = new Stars();
				star.setId(rs.getInt(1));
				star.setFirst_name(rs.getString(2));
				star.setLast_name(rs.getString(3));
				stars.add(star);
			}
			
			for(int i = 0;i < stars.size();i++) {
				List<Movies> moviesFromAStar = new ArrayList<Movies>();
				
				// Get movies from a certain genre
				sqlQuery = "SELECT * FROM movies WHERE movies.id IN "
						+ "(SELECT movie_id FROM stars_in_movies "
						+ "WHERE stars_in_movies.star_id = " + ((Stars) stars.get(i)).getId() + ") "
								+ "limit 10";
						
				rs = st.executeQuery(sqlQuery);
				while(rs.next()) {
					Movies movie = new Movies( rs.getInt(1),
							  rs.getString(2),
							  rs.getInt(3),
							  rs.getString(4),
							  rs.getString(5),
							  rs.getString(6),
							  rs.getFloat(7));
					moviesFromAStar.add(movie);
				}
				starMovies.add(moviesFromAStar);
			}
			
			rs = st.executeQuery(sqlQuery3);
			
			while(rs.next()) {
				Movies movie = new Movies(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getFloat(7));
				mostLikedMovies.add(movie);
			}
			


		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
		}
		
		request.setAttribute("genreNames", genres);
		request.setAttribute("moviesFromRandomGenres", genreMovies);
		
		request.setAttribute("starNames", stars);
		request.setAttribute("moviesFromRandomStars", starMovies);
		
		request.setAttribute("mostLiked", mostLikedMovies);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/main.jsp");
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
