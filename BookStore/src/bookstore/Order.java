package bookstore;

import java.util.List;

public class Order {

	private List<Book> books;
	private String id;
	
	public double computeCost() {
		double totalCost = 0;
		for(Book book : books) {
			totalCost += book.computeCost();
		}
		return totalCost;
	}
}
