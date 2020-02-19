package command;

public class RotateCommand extends RobotCommand {
	private double leftRotation;

	public RotateCommand(Robot robot, double leftRotation) {
		super(robot);
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
