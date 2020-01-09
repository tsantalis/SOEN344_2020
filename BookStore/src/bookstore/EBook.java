package bookstore;

public class EBook extends Book {

	private double discount;
	private int sizeMB;
	@Override
	public double computeCost() {
		return price - discount * price;
	}

}
