package chainOfResponsibility;

public abstract class Logger {
	public enum LogLevel {
		ERR, NOTICE, DEBUG;
	}

	protected LogLevel mask;
    
    public Logger(LogLevel mask) {
        this.mask = mask;
    }

    public abstract void writeMessage(String msg, LogLevel priority);
}
