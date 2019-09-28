package model.commands.turtle_commands;
/**
 * An abstract command subclass that stores a TurtleTracker
 * @author andrew
 * 
 */
import model.TurtleTracker;
import model.commands.Command;

public abstract class TurtleCommand extends Command {
	private TurtleTracker myTurtles;
	public TurtleCommand(TurtleTracker t, int numVars) {
		super(numVars);
		myTurtles = t;
	}
	/**
	 * Gets the TurtleTracker
	 * @return the TurtleTracker associated with this object
	 */
	protected TurtleTracker getTurtles() {
		return myTurtles;
	}

}
