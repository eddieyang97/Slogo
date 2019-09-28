package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * makes turtle invisible
 * returns 0
 */
public class HideTurtle extends TurtleAction {
	
	public HideTurtle(TurtleTracker t) {
		super(t, 0);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		myTurtle.makeInvisible();
		return 0;
	}
}