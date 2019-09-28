package model.commands.turtle_commands;
/**
 * An abstract subclass of TurtleCommand that defines some behavior for the act method, allowing the subclass commands to act on each Turtle
 * @author andrew
 */
import model.Turtle;
import model.TurtleTracker;

public abstract class TurtleAction extends TurtleCommand {
	public TurtleAction(TurtleTracker t, int numVars) {
		super(t,numVars);
	}
	/**
	 * This implementation of act checks whether the TurtleTracker is actively being looped over by another TurtleCommand.
	 * If not, it loops over the TurtleTracker and executes actOn() for each active turtle.
	 * Else, it executes actOn() for the specific Turtle currently being run.
	 * @return result of calling actOn() on the last turtle.
	 */
	@Override
	public double act(int index) {
		if(getTurtles().getIndex() != -1)
			return actOn(getTurtles().getTurtle(),index);
		double value = 0;
		for (int x = 0; x < getTurtles().getSize(); x++) {
			if (getTurtles().get(x).isActive()) {
				getTurtles().setIndex(x);
				value = actOn(getTurtles().getTurtle(), index);
			}
		}
		getTurtles().setIndex(-1);
		return value;
	}
	/**
	 * Calls a command on the specific Turtle and returns the result.
	 * Specific behavior to be determined by subclasses of TurtleCOmmand.
	 * @param t the Turtle to be called
	 * @return the result of running command
	 */
	public abstract double actOn(Turtle t, int index);

}
