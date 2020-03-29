package largeClass;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Creates a database connection.
 * 
 * The details of the Database URL and name of the driver can be 
 * configured in the configuration.xml file.
 * 
 * The constructor could fail if any of the arguments are null, the db
 * driver could not be found or a connection could not be created.
 * 
 * @author anisbet
 *
 */
public class Database {
	private DatabaseConnection databaseConnection = null;
	
	public Database(String driver, String url, String user, String password)
		throws UnableToCreateDatabaseException 
	{
		this.databaseConnection = new DatabaseConnection(driver, url, user, password);
		
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
	
	/**
     * Creates a Statement in such a way so that the result set
     * can be traversed both forward and backward, as well as
     * being read-only.
     * @param connection the connection to the database where
     * the statement should be created
     * @return the statement created, null otherwise
     */
    private Statement createStatement() {
    	
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
	
    
    public int getLastId() {
    	int lastId = 0;
		try {
			Statement statement = createStatement();
			ResultSet resultSet = null;
			resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
		
		
			lastId = 0;
			if (resultSet.next()) {
		        lastId = resultSet.getInt(1);
		    } else {
		        return 0;
		    }
		} catch (SQLException sqle) { // error occured while dropping table
			System.err.println("Last_insert_id failed.");
			return 0;
		}
		
		return lastId;
    	
    }
	
	/**
	 * Creates a table 
	 * @param table the name of the table.
	 * @param columnName the names of the columns or fields of the table.
	 * @param dataType The data types of the fields.
	 * @param primaryKey The index to the columnName that is the primary key. If you do not
	 * wish any primary key to be specified, set primaryKey to -1 or a number larger than the number
	 * @return true if the table was created successfully and false otherwise.
	 */
	public boolean createTable(String table, String[] columnName, String[] dataType, int primaryKey){
		// Table should look like this by the end:
		//CREATE TABLE JJJJData (
		//		   pKey       INTEGER      NOT NULL  AUTO_INCREMENT,
		//		   Customer   VARCHAR (20),
		//		   PRIMARY KEY( pKey )
		//		                     )
		
		if (table == null || table.length() < 1){
			System.err.println("Table's name must not be empty or null. Table create failed.");
			return false;
		}

		// check to make sure columns and dataTypes have the same length.
		if(columnName.length != dataType.length){
			System.err.println("Mismatch of the number of columns and their data types. Table create failed.");
			return false;
		}
		
		Statement statement = createStatement();
		
		StringBuffer tableBuffer  = new StringBuffer();
		int lastElementIndex = columnName.length -1;
		
		for (int i = 0; i < columnName.length; i++){
			
			tableBuffer.append(columnName[i] + " " + dataType[i]);
			
			// Most databases will make the primary key NOT NULL but not all apparently.
			// so for safety and any future db development we add it.
			if(i == primaryKey){
				tableBuffer.append(" NOT NULL");
			}

			// is this not the last column?
			if (i < lastElementIndex){
				tableBuffer.append(",");	
			} else {
				// If the primary key is in range of the number of columns
				// set that now by indicating which column, at the end of the table.
				if (primaryKey >= 0 && primaryKey <= lastElementIndex){
					tableBuffer.append(", ");
					tableBuffer.append("PRIMARY KEY( " + columnName[primaryKey] + " )");
				} // end if
			} // end else
		} // end for
		
		// try creating the table
		try {

			System.err.println("CREATE TABLE " + table + " (" + tableBuffer.toString() + ")");
			statement.executeUpdate("CREATE TABLE " + table + " (" + tableBuffer.toString() + ")");
			
		} catch (SQLException e) {
			System.err.println("Failed to create table '" + table + "'.");
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	
	/**
	 * Drops the table from the database.
	 * @param table the name of the table to drop
	 * @return true if drop is successful, false otherwise.
	 */
	public boolean dropTable(String table){

		Statement statement = createStatement();
		
		try {
			
			statement.executeUpdate("DROP TABLE " + table);
		
		} catch (SQLException sqle) { // error occured while dropping table
			System.err.println("Dropping the table '" + table + "' failed");
			return false;
		}
		
		return true;
	}
	
	
	
	/**
	 * @param name of table to test.
	 * @return true if the table exists and false otherwise.
	 */
	public boolean hasTable(String table){
		
		Statement statement = createStatement();
		
		// try querying the table, if it fails, table does not exist
		try {
				
			statement.executeQuery("SELECT * FROM " + table + " LIMIT 1");
				
		} catch (SQLException sqle) {
			return false;
		}
		
		return true;
		
	}
	
	
	
	/**
	 * Inserts the argument values into the argument table.
	 * @param table The table to insert the values into.
	 * @param table The names of the columns to use, none are specified if null.
	 * @param values the values to insert into the table.
	 * @return true if the insert was successful and false otherwise.
	 */
	public boolean insert(String table, String[] cols, String[] values){
		
		if (cols != null && cols.length != values.length) {
			System.err.println("You must have the same number of columns and values.");
			return false;
		}
		
		String columnText = "";
		if (cols != null) {
			StringBuffer columns = new StringBuffer("(");
			for (int i = 0; i < values.length; i++){
				
				// quoted strings when inserting but all values that could be interpreted as keywords in SQL.
				columns.append(cols[i]);
				
				if (i < cols.length -1){
					columns.append(",");
				}
			}
			columns.append(")");
			columnText = columns.toString();
		}
		else {
			columnText = "";
		}
		
		
		StringBuffer value = new StringBuffer();
		for (int i = 0; i < values.length; i++){
			
			// quoted strings when inserting but all values that could be interpreted as keywords in SQL.
			value.append("'" + values[i] + "'");
			
			if (i < values.length -1){
				value.append(",");
			}
		}
		
		return this.insert(table, columnText, value.toString());
	
	}
	
	public boolean insert(String table, String columnText, String values){
		
		Statement statement = createStatement();

		// try the insert
		try {
			
			//System.err.println("INSERT INTO " + table + " VALUES (" + value + ")");
			// The connection can be closed when you try to INSERT. This happens in 
			// the Simulation because AspectJ will close a connection when the simulation
			// finishes but it takes an additional network increment before that happens 
			// so when the increment occurs the state of an End point changes and wants 
			// to write to the database, which is now closed.
			if (isClosed()){
				return false;
			} else {
				statement.executeUpdate("INSERT INTO " + table + columnText + " VALUES (" + values + ")");
			}
			
		} catch (SQLException sqle) {
			System.err.println("Insert failed");
			sqle.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	private boolean isClosed() throws SQLException {
		return connection.isClosed();
	}

	
	
	
	/**
	 * Performs a SQL select statement returning the string value of the first value in the result set.
	 * @param table
	 * @param column
	 * @param whereClause
	 * @return the first result of the query result.
	 */
	public ResultSet select(String column, String table, String whereClause, String orderbyClause){
		
		Statement statement = createStatement();
		
		ResultSet resultSet = null;
		StringBuffer query  = new StringBuffer("SELECT " + column + " FROM " + table);
		
		if (whereClause != null && whereClause.length() > 0){
			query.append(" WHERE " + whereClause);
		}
		if (orderbyClause != null && orderbyClause.length() > 0) {
			query.append(" ORDER BY " + orderbyClause);
		}
		
		try {
			statement.setFetchSize(1000);
			//System.out.println(query.toString());
			resultSet = statement.executeQuery(query.toString());
			// Why, oh why, oh why, do we call resultSet.next() here?
			boolean found = resultSet.next();
			if (!found) {return null;} 
		} catch (SQLException sqle) {
			System.err.println("select() failed.");
			sqle.printStackTrace();
		}
		
		return resultSet;
		
	}
	
	
	public ResultSet select(String column, String table, String whereClause){
		return select(column, table, whereClause, null);
	}
	
	public ResultSet select(String column, String table){
		return select(column, table, null, null);
	}
	
	/**
	 * For testing purposes.
	 * @param args
	 */
	public static void main(String[] args) {
		
		if (args.length != 4){ // userid password
			
			System.err.println("Error: incorrect number of command line args.");
			System.err.println("Usage: java Database <driver> <url> <userid> <password>");
			System.exit(-1);
			
		}
		
		Database database = null;
		try{
			database = new Database(args[0], args[1], args[2], args[3]);
		} catch (UnableToCreateDatabaseException e){
			System.out.println("Ok, that failed what do we have?");
		}
		String[] columns = new String[2];
		columns[0] = "studentId";
		columns[1] = "studentName";
		String[] dataTypes = new String[2];
		dataTypes[0] = "INTEGER";
		dataTypes[1] = "VARCHAR (20)";
		// Entry      INTEGER      NOT NULL,
		// Customer   VARCHAR (20) NOT NULL,
		
		//String tableName = null; // fails - true.
		//String tableName = "";   // fails - true.
		String tableName = "test_01"; // passes - true.
		
		if (database.hasTable(tableName)){
			System.out.println("the table exists and will be deleted.");
			if (database.dropTable(tableName) == true){
				System.out.println("table dropped.");
			}
		}
		
		String[] values = { "100", "andrew" };
		
		database.createTable(tableName, columns, dataTypes, 0);      // passes - true.
		database.insert(tableName, null, values);                          // passes - true.
		ResultSet resultSet = database.select("*", tableName, null); // passes - true.
		try {
			System.out.println("results: " + resultSet.getString(2).toString()); // passes - true
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (database.dropTable(tableName) == true){                  // passes - true.
			System.out.println("END: table dropped.");               // passes - true.
		}
		database.closeConnection();                                  // passes - true.
		
	}
}
