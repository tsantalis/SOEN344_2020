package chainOfResponsibility;

import chainOfResponsibility.Logger.LogLevel;

public class Client {
	private static StderrLogger errLogger = new StderrLogger(LogLevel.ERR);
	private static EmailLogger emailLogger = new EmailLogger(LogLevel.NOTICE, errLogger);
	private static StdoutLogger outLogger = new StdoutLogger(LogLevel.DEBUG, emailLogger);
	
	
	
	public static void main(String[] args) {
		message("Entering function y.", LogLevel.DEBUG);
		message("Step1 completed.", LogLevel.NOTICE);
		message("An error has occurred.", LogLevel.ERR);
	}

	private static void message(String msg, LogLevel priority) {
		outLogger.writeMessage(msg, priority);
	}
}
