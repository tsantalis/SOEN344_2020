package command;

import java.util.LinkedList;
import java.util.Stack;

//plays the role of the Invoker in the Command design pattern
public class RobotController {

	private LinkedList<RobotCommand> executionQueue = new LinkedList<RobotCommand>();
	private Stack<RobotCommand> undoStack = new Stack<RobotCommand>();
	
	public RobotController() {
		
	}

	public void addCommand(RobotCommand command) {
		executionQueue.add(command);
	}

	public void executeCommands() {
		System.out.println("EXECUTING COMMANDS.");
		while(!executionQueue.isEmpty()) {
			RobotCommand command = executionQueue.removeFirst();
			command.execute();
			undoStack.add(command);
		}
	}

	public void undoCommands(int numUndos) {
		System.out.format("REVERSING %d COMMAND(S).%n", numUndos);
		for(int i=0; i<numberOfIterations(numUndos); i++) {
			undoStack.pop().undo();
		}
	}

	private int numberOfIterations(int numUndos) {
		return validInput(numUndos) ? numUndos : undoStack.size();
	}

	private boolean validInput(int numUndos) {
		return numUndos > 0 && numUndos <= undoStack.size();
	}
}
