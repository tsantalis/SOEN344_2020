package command;

public interface AbstractCommand {

	public abstract void execute();

	public abstract void undo();
}
