package chainOfResponsibility;

public class StdoutLogger extends Logger {

	private EmailLogger next;
    public StdoutLogger(LogLevel mask, EmailLogger logger) {
        super(mask);
        next = logger;
    }

    public void writeMessage(String msg, LogLevel priority) {
    	if (priority.compareTo(mask) <= 0) {
    		System.out.println("Writing to stdout: " + msg);
    	}
        if(next != null) {
        	next.writeMessage(msg, priority);
        }
    }

}
