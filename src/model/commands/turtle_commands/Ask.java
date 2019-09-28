package model.commands.turtle_commands;
/**
 * Implements the ask command.
 * @author andrew
 */
import model.TurtleTracker;
import model.commands.Command;

public class Ask extends TurtleAsker {
	public Ask(TurtleTracker t) {
		super(t);
	}

	@Override
	protected Command getListOfTurtles(int index) {
		return getChild(index);
	}

}
