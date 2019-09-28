package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * moves turtle backward in its current heading by pixels distance
 * returns the value of pixels
 */
public class Backward extends TurtleAction{
	
	public Backward(TurtleTracker t) {
		super(t, 1);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {

		double value = childValue(index);
		myTurtle.moveForward(-value);
		return value;
	}
}
