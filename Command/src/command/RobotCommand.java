package command;

public interface RobotCommand {

	public abstract void execute();

	public abstract void undo();
}
