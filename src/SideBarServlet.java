
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
 * Servlet implementation class SideBarServlet
 */
@WebServlet("/sidebar/*")
public class SideBarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SideBarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    List list = new ArrayList();

	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
			Statement select = connection.createStatement();
		    ResultSet result = select.executeQuery("select name from genres"); 		
		    
		    if(result.absolute(1))
		    {
		    	while(result.next())
		    	{
		    		list.add(result.getString(1));
		    		/*
		    		 * 	movies.add(rs.getString(1));
				movies.add(rs.getString(2));
				list.add(movies);
		    		 */
		    	}
		    }
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    	    request.setAttribute("genresList", list);
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/_sidebarView.jsp");
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
