package chainOfResponsibility;

import chainOfResponsibility.Logger.LogLevel;

public class Client {
	private static StdoutLogger outLogger = new StdoutLogger(LogLevel.DEBUG);
	private static EmailLogger emailLogger = new EmailLogger(LogLevel.NOTICE);
	private static StderrLogger errLogger = new StderrLogger(LogLevel.ERR);
	
	public static void main(String[] args) {
		message("Entering function y.", LogLevel.DEBUG);
		message("Step1 completed.", LogLevel.NOTICE);
		message("An error has occurred.", LogLevel.ERR);
	}

	private static void message(String msg, LogLevel priority) {
		if(priority == LogLevel.DEBUG) {
			outLogger.writeMessage(msg);
		}
		else if(priority == LogLevel.NOTICE) {
			outLogger.writeMessage(msg);
			emailLogger.writeMessage(msg);
		}
		else if(priority == LogLevel.ERR) {
			outLogger.writeMessage(msg);
			emailLogger.writeMessage(msg);
			errLogger.writeMessage(msg);
		}
	}
}
