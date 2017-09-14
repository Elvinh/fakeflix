import java.util.HashMap;

public class ShoppingCart {
	private HashMap<String, Integer> items;
	
	public ShoppingCart() {
		items = new HashMap<>();
	}
	
	public HashMap getCart() {
		return items;
	}
	public void addToCart(String item, int price) {
		items.put(item,  price);
	}
}
