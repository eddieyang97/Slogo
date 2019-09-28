package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * puts pen up such that when the turtle moves, it does not leave a trail
 * returns 0
 */
public class PenUp extends TurtleAction {
	
	public PenUp(TurtleTracker t) {
		super(t, 0);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		myTurtle.penUp();
		return 0;
	}
}