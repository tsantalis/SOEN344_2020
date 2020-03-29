package largeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	/**
     * Creates a Statement in such a way so that the result set
     * can be traversed both forward and backward, as well as
     * being read-only.
     * @param connection the connection to the database where
     * the statement should be created
     * @return the statement created, null otherwise
     */
    public Statement createStatement() {
    	
        Statement statement = null;
        
        try {
        	
        	if (connection == null){
            	System.err.println(this.getClass().getName() + "Error: unable to create Statement object.");
            	System.exit(-1);
            }
        	
            statement = connection.createStatement(
            		ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

        } catch (SQLException sqle) {
            System.err.println("Could not create statement");
            sqle.printStackTrace();
        }
        
        return statement;
    }

    /**
	 * Closes the connection to the database.
	 */
	public void closeConnection(){
		
		try {
			
			this.connection.close();
			System.out.println("Database connection closed.");
			
		} catch (SQLException sqle) {
			System.err.println("Can not close the database connection");
			sqle.printStackTrace();
		}
	}

	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}

}
