package model.commands.turtle_commands;
/**
 * Implements the tell command.
 * Tell command ignores all Turtle IDs that are not integers.
 * @author andrew
 */
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import model.SLogoException;
import model.TurtleTracker;
import model.commands.Command;
import model.commands.ListCommand;

public class Tell extends TurtleCommand {

	public Tell(TurtleTracker t) {
		super(t, 1);
	}
	@Override
	public double act(int index) {
		Set<Double> actives = new HashSet<>();
		Command c = getChild(index);
		if(!(c instanceof ListCommand))
			throw new SLogoException("Invalid arguments for turtle activation");
		
		for(int y = 0; y < c.howManyArguments(); y++)
			actives.add(c.getChild(y).run());
		if(!actives.isEmpty())
			while(getTurtles().getSize() < Collections.max(actives))
				getTurtles().add();
		
		getTurtles().setActiveTurtles(actives);
		if(actives.isEmpty())
			return 0;
		return Collections.max(actives);
	}

}
