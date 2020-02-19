package command;

public class MoveCommand extends RobotCommand {
	private int forwardDistance;

	public MoveCommand(Robot robot, int forwardDistance) {
		super(robot);
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
