package chainOfResponsibility;

public class StdoutLogger extends Logger {

    public StdoutLogger(LogLevel mask) {
        super(mask);
    }

    public void writeMessage(String msg) {
        System.out.println("Writing to stdout: " + msg);
    }

}
