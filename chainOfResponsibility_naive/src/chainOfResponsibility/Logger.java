package chainOfResponsibility;

public abstract class Logger {
	public enum LogLevel {
		ERR, NOTICE, DEBUG;
	}

	private LogLevel mask;
    
    public Logger(LogLevel mask) {
        this.mask = mask;
    }

    public abstract void writeMessage(String msg);
}
