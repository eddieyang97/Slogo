package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * moves turtle to an absolute screen position, where (0, 0) is the center of the screen
 * returns the distance turtle moved
 */
public class SetPosition extends TurtleAction {

	public SetPosition(TurtleTracker t) {
		super(t, 2);
	}

	@Override
	public double actOn(Turtle myTurtle, int index) {
		double xcor = childValue(index);
		double ycor = childValue(index+1);
		double distance = Math.hypot(xcor - myTurtle.getXPosition(), ycor - myTurtle.getXPosition());
		myTurtle.moveTo(xcor, ycor);
		return distance;
	}
}