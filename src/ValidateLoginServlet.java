

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
 * Servlet implementation class ValidateLoginServlet
 */
@WebServlet("/user/*")

public class ValidateLoginServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	    Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		List nameList = new ArrayList();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try
		{
	    	Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
			st = conn.createStatement();
		    rs = st.executeQuery("Select *  from customers where customers.email = '"+ email + "' and customers.password = '" + password + "'");
	    	HttpSession session = null;

		    if(rs.absolute(1))
		    {
		    	session = request.getSession();
		    
		    	rs2 = st.executeQuery("Select first_name, last_name from customers where customers.email ='"+ email + "' and customers.password = '" + password + "'");
			    while(rs2.next())
			    {
			    	nameList.add(rs2.getString(1));
			    	nameList.add(rs2.getString(2));

			    }
			  
			    
			    session.setAttribute("loginedU", (String) nameList.get(0) + " " + nameList.get(1));
			    
		    	request.setAttribute("email", email);
			    request.setAttribute("nameList", nameList);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/userInfo.jsp");
				dispatcher.forward(request, response);

		    }
		    else
		    {
		    	request.setAttribute("badEmail", email);
		    	request.setAttribute("badPW", password);
		    	
		    	response.sendRedirect(request.getContextPath() + "/loginForm.jsp");

		    }
		  
		}
		 catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(rs2);
			DbUtils.closeQuietly(st);
			DbUtils.closeQuietly(conn);
		}

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
