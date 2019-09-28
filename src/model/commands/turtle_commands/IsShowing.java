package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * returns 1 if turtle is showing, 0 if it is hiding
 */
public class IsShowing extends TurtleAction{
	
	public IsShowing(TurtleTracker t) {
		super(t, 0);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		return (myTurtle.isShowing()) ? 1:0;
	}
}
