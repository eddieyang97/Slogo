package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;


/**
 * 
 * @author jeffreyli
 * returns ID of current active turtle
 */

public class ID extends TurtleAction {

	public ID(TurtleTracker t) {
		super(t, 0);
	}

	@Override
	public double actOn(Turtle t, int index) {
		return t.getID();
	}

}
