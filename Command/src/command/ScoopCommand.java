package command;

public class ScoopCommand extends RobotCommand {
	private boolean scoopUpwards;

	public ScoopCommand(Robot robot, boolean scoopUpwards) {
		super(robot);
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
