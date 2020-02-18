package command;

//plays the role of the Invoker in the Command design pattern
public class RobotController {

	public RobotController() {
		
	}

	public void addCommand(RobotCommand command) {
		
	}

	public void executeCommands() {
		System.out.println("EXECUTING COMMANDS.");
		
	}

	public void undoCommands(int numUndos) {
		System.out.format("REVERSING %d COMMAND(S).%n", numUndos);
		
	}
}
