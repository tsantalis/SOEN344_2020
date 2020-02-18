package command;

public class RotateCommand implements RobotCommand {
	private double leftRotation;
	private Robot _robot;

	public RotateCommand(Robot robot, double leftRotation) {
		this._robot = robot;
		this.leftRotation = leftRotation;
	}

	@Override
	public void execute() {
		_robot.rotateLeft(leftRotation);
	}

	@Override
	public void undo() {
		_robot.rotateLeft(-leftRotation);
	}
}
