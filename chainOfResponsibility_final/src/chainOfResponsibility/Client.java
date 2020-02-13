package chainOfResponsibility;

import chainOfResponsibility.Logger.LogLevel;

public class Client {

	public static void main(String[] args) {
		// Build the chain of responsibility
        Logger l = new StdoutLogger(LogLevel.DEBUG);
        l
        .setNext(new EmailLogger(LogLevel.NOTICE))
        .setNext(new StderrLogger(LogLevel.ERR));

        // Handled by StdoutLogger
        l.message("Entering function y.", LogLevel.DEBUG);

        // Handled by StdoutLogger and EmailLogger
        l.message("Step1 completed.", LogLevel.NOTICE);

        // Handled by all three loggers
        l.message("An error has occurred.", LogLevel.ERR);
	}

}
