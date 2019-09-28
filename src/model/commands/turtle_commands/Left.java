package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * turns turtle counterclockwise by degrees angle
 * returns the value of degrees
 */
public class Left extends TurtleAction {

	public Left(TurtleTracker t) {
		super(t, 1);
	}

	@Override
	public double actOn(Turtle myTurtle, int index) {
		double value = childValue(index);
		myTurtle.rotate(value);
		return value;
	}
}
