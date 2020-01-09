package bookstore;

public abstract class Book {

	private String title;
	private String author;
	protected double price;
	
	public abstract double computeCost();
}
