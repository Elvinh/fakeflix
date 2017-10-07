import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

public class ShoppingCart
{
	private HashMap<String, Movies> items;
	
	public ShoppingCart() 
	{
		items = new HashMap<>();
	}
	
	public HashMap getCart() 
	{
		return items;
	}
	public void addToCart(String item, Movies movie) 
	{
		items.put(item,  movie);
	}
	
	/*public HashMap getCart(String userID)
	{
		//MOVE ALL THIS TO VALIDATE LOGIN SERVLET
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	    	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", "root", "admin");
			st = conn.createStatement();
		    //rs = st.executeQuery("Select movieID, quantity from shoppingCart where shoppingCart.customerID = " + userID +"");
			//select movies.title, shoppingcart.quantity from movies, shoppingcart where shoppingCart.customerID = 2 and movies.id = shoppingCart.movieID;
		    rs = st.executeQuery("select movies.title, shoppingcart.quantity from movies inner join shoppingcart on movies.id = shoppingcart.movieID and shoppingCart.customerID = " + userID + "");
		    while(rs.next()) 
		    {
				items.put(rs.getString(1), rs.getInt(2));
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
		
		return items;
		
	}*/
}
