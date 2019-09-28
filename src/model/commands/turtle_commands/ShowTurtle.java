package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * makes turtle visible
 * returns 1
 */
public class ShowTurtle extends TurtleAction {
	
	public ShowTurtle(TurtleTracker t) {
		super(t, 0);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		myTurtle.makeVisible();
		return 1;
	}
}