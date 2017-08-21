
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
		
	    Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
	    try
	    {
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
			st = conn.createStatement();
		    rs = st.executeQuery("select name from genres"); 		
		    
		    if(rs.absolute(1))
		    {
		    	while(rs.next())
		    	{
		    		list.add(rs.getString(1));
		    		/*
		    		 * 	movies.add(rs.getString(1));
				movies.add(rs.getString(2));
				list.add(movies);
		    		 */
		    	}
		    }
	    }
	    catch(SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
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
