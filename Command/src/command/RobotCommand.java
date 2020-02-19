package command;

public abstract class RobotCommand implements AbstractCommand {
	protected Robot _robot;

	public RobotCommand(Robot robot) {
		this._robot = robot;
	}
}
