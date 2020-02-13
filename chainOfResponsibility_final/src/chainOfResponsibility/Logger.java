package chainOfResponsibility;


public abstract class Logger {

	public enum LogLevel {
		ERR, NOTICE, DEBUG;
	}

	private LogLevel mask;

    // The next element in the chain of responsibility
    private Logger next;

    public Logger(LogLevel mask) {
        this.mask = mask;
    }

    public Logger setNext(Logger l) {
        next = l;
        return l;
    }

    public void message(String msg, LogLevel priority) {
        if (priority.compareTo(mask) <= 0) {
            writeMessage(msg);
        }
        if (next != null) {
            next.message(msg, priority);
        }
    }
   
    abstract protected void writeMessage(String msg);

}