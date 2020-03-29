package largeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private Connection connection = null;
	
	public DatabaseConnection(String driver, String url, String user, String password)
		throws UnableToCreateDatabaseException 
	{
		
		try{
			
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (NullPointerException npe){
			throw new UnableToCreateDatabaseException("Error: a Database constructor argument is null.");
		} catch (ClassNotFoundException e) {
			throw new UnableToCreateDatabaseException("Error: database driver not found.");
		} catch (SQLException sqle) {
			throw new UnableToCreateDatabaseException("Error: Could not connect to requested Database.");
		}
		
		System.out.println("Successfully connected to database.");
		
	}
}
