package command;

public class MoveCommand implements RobotCommand {
	private int forwardDistance;
	private Robot _robot;

	public MoveCommand(Robot robot, int forwardDistance) {
		this._robot = robot;
		this.forwardDistance = forwardDistance;
	}

	@Override
	public void execute() {
		_robot.move(forwardDistance);
	}

	@Override
	public void undo() {
		_robot.move(-forwardDistance);
	}
}
