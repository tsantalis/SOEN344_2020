package bookstore;

import java.util.List;

public class Customer {

	private List<Order> orders;
	private String name;
	
	public double computeCost() {
		double totalCost = 0;
		for(Order order : orders) {
			totalCost += order.computeCost();
		}
		return totalCost;
	}
}
