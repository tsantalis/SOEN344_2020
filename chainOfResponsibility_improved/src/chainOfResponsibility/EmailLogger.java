package chainOfResponsibility;

public class EmailLogger extends Logger {

	private StderrLogger next;
    public EmailLogger(LogLevel mask, StderrLogger logger) {
        super(mask);
        next = logger;
    }

    public void writeMessage(String msg, LogLevel priority) {
    	if (priority.compareTo(mask) <= 0) {
    		System.out.println("Sending via email: " + msg);
    	}
        if(next != null) {
        	next.writeMessage(msg, priority);
        }
    }

}
