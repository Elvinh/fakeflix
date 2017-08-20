

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

@WebServlet("/browse/*")

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
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
		
		String browseType = request.getParameter("browseBy");
		String orderBy = request.getParameter("orderBy");
		String page = request.getParameter("page");
		String gName = request.getParameter("genreName");

		if(page == null) {
			page = "1";
		}
		if(browseType == null) {
			browseType = "title";
		}
		
		int lower = (Integer.parseInt(page) - 1)  * 25;
		int higher = Integer.parseInt(page) * 25;
		String range = String.valueOf(lower) + ", "  + String.valueOf(higher);
		
		String sqlQuery = null;
		System.out.println(browseType);

		if(browseType.equals("title")) {
			sqlQuery = "SELECT title, banner_url  FROM movies ORDER BY " + orderBy + " LIMIT " + range;
		}
		else if(browseType.equals("genre"))
			sqlQuery = "SELECT name, id  FROM genres";
		else if(browseType.equals("genreName"))
		{
			sqlQuery = "SELECT title, banner_url FROM movies WHERE movies.id in (SELECT movie_id FROM genres_in_movies WHERE genres_in_movies.genre_id in (SELECT id FROM genres WHERE genres.name = \"" + gName + "\")) ORDER BY " + orderBy;
		}
		else if(browseType.equals("advSearch"))
		{
			
			String title = "";
			String year = "";
			String director = "";
			String aGenre = "";
			String fstar = "";
			String lstar = "";
			
			title = request.getParameter("advTitle");
			year = request.getParameter("advYear");
			director = request.getParameter("advDirector");
			aGenre = request.getParameter("advGenre");
			fstar = request.getParameter("advStarF");
			lstar = request.getParameter("advStarL");
			
			System.out.println("Here");
			if(title.equals(null))
			{
				title = "";
			}
			if(year.equals(null))
			{
				year = "";
			}
			if(director.equals(null))
			{
				director = "";
			}
			if(aGenre.equals(null))
			{
				aGenre = "";
			}
			if(fstar.equals(null))
			{
				fstar = "";
			}
			if(lstar.equals(null))
			{
				lstar = "";
			}
			
			System.out.println(title);
			System.out.println(year);
			System.out.println(director);
			System.out.println(aGenre);
			System.out.println(fstar);
			System.out.println(lstar);

			
			sqlQuery = "SELECT title, banner_url FROM movies WHERE movies.id in (SELECT movie_id FROM genres_in_movies WHERE genres_in_movies.genre_id in (SELECT id FROM genres WHERE genres.name LIKE '%" + aGenre + "%')) AND movies.id in (select movie_id from stars_in_movies where stars_in_movies.star_id in (select stars.id from stars where stars.first_name LIKE '%" + fstar + "%' AND '%" + lstar+ "%')) AND movies.title = '%" + title + "%' AND movies.year = '%" + year + "%' AND movies.director = '%" + director + "%' ORDER BY "  + orderBy;
			
			// select * from movies where movies.id in (select movie_id from stars_in_movies where stars_in_movies.star_id in (select stars.id from stars where stars.first_name = 'Kristin'))
			// movies.id in (select movie_id from stars_in_movies where stars_in_movies.star_id in (select stars.id from stars where stars.first_name LIKE '%' + fstar + '%' and '%' + lstar+ '%'))
			// select * from movies where movie.title like "%" + title + "%" and movies.year like "%%"
		}
		
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
		List stuff = new ArrayList();
		stuff = (List) list.get(0);
		System.out.println(stuff.get(0));
		request.setAttribute("browseResult", list);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/browseResultView.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
