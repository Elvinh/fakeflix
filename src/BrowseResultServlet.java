

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

import org.apache.commons.dbutils.*;

@WebServlet("/browse/*")

public class BrowseResultServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		String url = "jdbc:mysql://localhost:3306/";
		String db = "moviedb";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String password = "admin";
		
		String browseType = request.getParameter("browseBy");
		String orderBy = request.getParameter("orderBy");
		String page = request.getParameter("page");
		String gName = request.getParameter("genreName");
		
		List list = new ArrayList();
		
		String sqlQuery = null;

		if(page == null) {
			page = "1";
		}
		if(browseType == null) {
			browseType = "title";
		}
		if(orderBy != null) {
			session.setAttribute("orderType", orderBy);
		}
		else{
			orderBy = (String) session.getAttribute("orderType");
			System.out.println(orderBy);
		}
		
		int lower = (Integer.parseInt(page) - 1)  * 25;
		int higher = Integer.parseInt(page) * 25;
		String range = String.valueOf(lower) + ", "  + String.valueOf(higher);

		//String sqlQuery = null;

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
			
			if(title == null)
			{
				title = "";
			}
			if(year == null)
			{
				year = "";
			}
			if(director == null)
			{
				director = "";
			}
			if(aGenre == null)
			{
				aGenre = "";
			}
			if(fstar == null)
			{
				fstar = "";
			}
			if(lstar == null)
			{
				lstar = "";
			}

			sqlQuery = "SELECT title, banner_url FROM movies "
					+ "WHERE movies.id in "
					+ "(SELECT movie_id FROM genres_in_movies WHERE genres_in_movies.genre_id in "
					+ "(SELECT id FROM genres WHERE genres.name LIKE '%" + aGenre + "%'))"
					+ " AND movies.id in "
					+ "(select movie_id from stars_in_movies where stars_in_movies.star_id in "
					+ "(select stars.id from stars where stars.first_name LIKE '%" + fstar + "%' AND stars.last_name LIKE '%" + lstar+ "%')) "
							+ "AND movies.title LIKE '%" + title + "%' "
									+ "AND movies.year LIKE '%" + year + "%' "
											+ "AND movies.director LIKE '%" + director + "%' "
													+ "ORDER BY "  + orderBy;
			
		}

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url+db, user, password);
			st = conn.createStatement();
			rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				List movies = new ArrayList();
				movies.add(rs.getString(1));
				movies.add(rs.getString(2));
				list.add(movies);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
		}

		request.setAttribute("browseResult", list);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/browseResultView.jsp");
		dispatcher.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
