package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;
/**
 * 
 * @author jeffreyli
 *moves turtle forward in its current heading by pixels distance
 * returns the value of pixels
 */
public class Forward extends TurtleAction {
	
	public Forward(TurtleTracker t) {
		super(t, 1);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		double value = childValue(index);
		myTurtle.moveForward(value);
		return value;
	}
}
