

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
 * Servlet implementation class ValidateLoginServlet
 */
@WebServlet("/user/*")

public class ValidateLoginServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List nameList = new ArrayList();
		try
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_db", "root", "lilwizzard1");
			Statement select = connection.createStatement();
		    ResultSet result = select.executeQuery("Select *  from customers where customers.email = '"+ email + "' and customers.password = '" + password + "'");

		    if(result.absolute(1))
		    {
		    	//HttpSession session = request.getSession();
		    
			    ResultSet rs = select.executeQuery("Select first_name, last_name from customers where customers.email ='"+ email + "' and customers.password = '" + password + "'");
			    while(rs.next())
			    {
			    	nameList.add(rs.getString(1));
			    	nameList.add(rs.getString(2));

			    }
			    //session.setAttribute("loginedU", rs.getString(1) + " " + rs.getString(2));
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
		catch(Exception e)
		{
			
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
