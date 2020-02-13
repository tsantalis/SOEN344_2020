package chainOfResponsibility;

public class StderrLogger extends Logger {

    public StderrLogger(LogLevel mask) {
        super(mask);
    }

    public void writeMessage(String msg) {
        System.err.println("Sending to stderr: " + msg);
    }

}
