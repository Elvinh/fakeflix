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
	private int count;
	
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
		items.put(item,  movie);
		count++;
	}
	public int getCount() {
		return count;
	}
	
	public void remove(String item, Movies movie)
	{
		System.out.println("I'm going to delete this " + item);
		items.remove(item);
		count--;
	}
	public void print()
	{
		if(items.containsKey("The Phantom of the Opera"))
		{
			System.out.println("Still have phantom of the opera in here");
		}
	}
}
