package largeClass;

/**
 * @author anisbet
 * Thrown if the construction of the database fails. This is to signal the calling class
 * that the database could not be created as specified but where the application may want
 * to continue on in spite of there being no database.
 */
public class UnableToCreateDatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1;
	protected UnableToCreateDatabaseException(String msg){
		System.err.println(msg);
	}
}