package bookstore;

import java.util.Date;
import java.util.List;

public class Customer {

	private List<Order> orders;
	private String name;
	private CreditCardInfo creditCard;
	
	public Customer(long creditCardNumber, Date expirationDate) {
		this.creditCard = new CreditCardInfo(creditCardNumber, expirationDate);
	}
	
	public double computeCost() {
		double totalCost = 0;
		for(Order order : orders) {
			totalCost += order.computeCost();
		}
		return totalCost;
	}
	
	private class CreditCardInfo {
		private long number;
		private Date expiration;
		
		public CreditCardInfo(long number, Date expiration) {
			this.number = number;
			this.expiration = expiration;
		}
	}
}
