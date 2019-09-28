package model.commands.turtle_commands;
/**
 * Implements the AskWith command 
 * @author andrew
 */
import model.TurtleTracker;
import model.commands.Command;
import model.commands.Constant;
import model.commands.ListCommand;

public class AskWith extends TurtleAsker {

	public AskWith(TurtleTracker t) {
		super(t);
	}
	@Override
	protected Command getListOfTurtles(int index) {
		ListCommand toActivate = new ListCommand();
		for(int y = 0; y < getTurtles().getSize(); y++) {
			getTurtles().setIndex(y);
			double potential = childValue(index);
			if(potential != 0)
				toActivate.addChild(new Constant(y+1));
		}
		return toActivate;
	}

}
