package command;

public class Client {

	public static void main(String[] args) {
		Robot robot = new Robot();
		RobotController controller = new RobotController();

		MoveCommand move = new MoveCommand(robot, 1000);
		controller.addCommand(move);

		RotateCommand rotate = new RotateCommand(robot, 45);
		controller.addCommand(rotate);

		ScoopCommand scoop = new ScoopCommand(robot, true);
		controller.addCommand(scoop);

		//the controller (i.e., invoker) can execute the commands asynchronously
		controller.executeCommands();
		controller.undoCommands(3);
	}

}
