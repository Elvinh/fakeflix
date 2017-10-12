import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

public class ShoppingCart
{
	private HashMap<String, Movies> items;
	private int count;
	public float total;
	
	public ShoppingCart() 
	{
		this.count = 0;
		items = new HashMap<>();
	}
	
	public HashMap getCart() 
	{
		return items;
	}
	public void addToCart(String item, Movies movie) 
	{
		int quantity = movie.getQuantity();
		items.put(item,  movie);
		count = count + quantity;
	}
	public void removeFromCart(String item, Movies movie)
	{
		int quantity = movie.getQuantity();
		items.remove(item);
		count = count - quantity;
	}
	public void print(String movieName)
	{
		if(items.containsKey(movieName))
		{
			System.out.println("Still have " + movieName);
		}
	}
	public int getCount() 
	{
		return count;
	}
	
	public void getTotal()
	{
		float total = 0;
		Collection<Movies> productListValues = items.values();
        
        //Creating an ArrayList of values
        ArrayList<Movies> result = new ArrayList<Movies>(productListValues);
        for(Movies movie: result)
        {
        	total = total + movie.getPrice()*movie.getQuantity();
        }
		this.total = total;
		System.out.println(total);
	}
	
	public boolean isEmpty()
	{
		return items.isEmpty();
	}
}
