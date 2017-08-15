

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
@WebServlet("/MoviesByStar")
public class MoviesByStar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviesByStar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		List list = new ArrayList();

		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "e951l632v";
		
		String selectedType = request.getParameter("selected");
		String type = null;
		String sqlQuery = null;
		
		
		sqlQuery = "SELECT title, year, director, banner_url, trailer_url FROM movies WHERE movies.title = '" + selectedType + "'";
		
		try {
			Class.forName(driver);
			Connection connection;
			connection = DriverManager.getConnection(url+db, user, password);
			Statement st;
			st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			
			if(rs.absolute(1))
			{
				System.out.println("works");
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
			}
			else {
				sqlQuery = "SELECT title FROM movies WHERE movies.id in (SELECT movie_id FROM genres_in_movies WHERE genres_in_movies.genre_id in (SELECT id FROM genres WHERE genres.name = '" + selectedType + "'))";
				rs = st.executeQuery(sqlQuery);
				while (rs.next())
		        {
					list.add(rs.getString(1));
		        }
				type = "genre";
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("requestedMovie", list);
		request.setAttribute("type", type);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/getMovie.jsp");
		dispatcher.forward(request, response);
		
	}

}
