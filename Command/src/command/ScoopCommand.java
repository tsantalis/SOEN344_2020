package command;

public class ScoopCommand implements RobotCommand {
	private boolean scoopUpwards;
	private Robot _robot;

	public ScoopCommand(Robot robot, boolean scoopUpwards) {
		this._robot = robot;
		this.scoopUpwards = scoopUpwards;
	}

	@Override
	public void execute() {
		_robot.scoop(scoopUpwards);
	}

	@Override
	public void undo() {
		_robot.scoop(!scoopUpwards);
	}

}
