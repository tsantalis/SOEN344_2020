package chainOfResponsibility;

public class StderrLogger extends Logger {

    public StderrLogger(LogLevel mask) {
        super(mask);
    }

    public void writeMessage(String msg, LogLevel priority) {
    	if (priority.compareTo(mask) <= 0) {
    		System.err.println("Sending to stderr: " + msg);
    	}
    }

}
