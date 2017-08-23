

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
		List<List<Movies>> movies = new ArrayList<List<Movies>>();
		List genreNames = new ArrayList();

		// Get genres
		String sqlQuery1 = "SELECT name FROM genres";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url+db, user, password);
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery1);
			
			while(rs.next()) {
				genres.add(rs.getString(1));
			}
			
			// Generate 5 random indexes to choose random genres
			final int [] randomGenres = new Random().ints(0,genres.size()).distinct().limit(5).toArray();
			
			System.out.println(randomGenres.length);

			for(int i = 0;i < randomGenres.length;i++) {
				List<Movies> moviesFromAGenre = new ArrayList<Movies>();

				System.out.println(randomGenres[i]);
				
				// Choose 5 random genres to get movies from
				String genreName = (String) genres.get(randomGenres[i]);
				
				genreNames.add(genreName);
				System.out.println(genreName);
				
				// Get movies from a certain genre
				String sqlQuery2 = "SELECT title, banner_url FROM movies WHERE movies.id in "
						+ "(SELECT movie_id FROM genres_in_movies WHERE genres_in_movies.genre_id in "
						+ "(SELECT id FROM genres WHERE genres.name = \"" + genreName + "\")) "
						+ "limit 10";
				
				rs = st.executeQuery(sqlQuery2);
				while(rs.next()) {
					System.out.print(rs.getString(1));

					Movies movie = new Movies();
					
					movie.setTitle(rs.getString(1));
					movie.setBanner_url(rs.getString(2));
					
					moviesFromAGenre.add(movie);

					System.out.println("");
				}
				movies.add(moviesFromAGenre);
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
		}
		
		System.out.println(movies.size());
		for(int i=0;i<movies.size();i++) {
			List<Movies> movie = movies.get(i);
			System.out.println();
			for(int j=0;j<movie.size();j++) {
				System.out.print(movie.get(j).getTitle());
			}
		}
		
		request.setAttribute("genreNames", genreNames);
		request.setAttribute("moviesFromRandomGenres", movies);
		
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
