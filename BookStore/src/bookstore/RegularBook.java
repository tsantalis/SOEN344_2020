package bookstore;

public class RegularBook extends Book {

	private double shippingCost;
	
	@Override
	public double computeCost() {
		return price + shippingCost;
	}

}
