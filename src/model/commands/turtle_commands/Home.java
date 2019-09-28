package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * moves turtle to the center of the screen (0 0)
 * returns the distance turtle moved
 */
public class Home extends TurtleAction {

	public Home(TurtleTracker t) {
		super(t, 0);
	}

	@Override
	public double actOn(Turtle myTurtle, int index) {
		double distance = Math.hypot(myTurtle.getXPosition(), myTurtle.getXPosition());
		myTurtle.moveTo(0, 0);
		return distance;
	}
}