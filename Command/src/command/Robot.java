package command;

//plays the role of Receiver in the Command design pattern
public class Robot {
	public void move(int forwardDistance) {
		if (forwardDistance > 0)
			System.out.format("Robot moved forwards %d mm.%n", forwardDistance);
		else
			System.out.format("Robot moved backwards %d mm.%n", -forwardDistance);
	}

	public void rotateLeft(double leftRotation) {
		if (leftRotation > 0)
			System.out.format("Robot rotated left %.1f degrees.%n", leftRotation);
		else
			System.out.format("Robot rotated right %.1f degrees.%n", -leftRotation);
	}

	public void scoop(boolean upwards) {
		if (upwards)
			System.out.format("Robot gathered soil in scoop.%n");
		else
			System.out.format("Robot released scoop contents.%n");
	}
}
