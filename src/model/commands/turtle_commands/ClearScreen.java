package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli erases turtle's trails and sends it to the home position
 *         returns the distance turtle moved
 */
public class ClearScreen extends TurtleAction {

	public ClearScreen(TurtleTracker t) {
		super(t, 0);
	}

	@Override
	public double act(int index) {
		double value = 0;
		for (int x = 0 ; x < getTurtles().getSize(); x++) {
			value = actOn(getTurtles().get(x),index);
		}
		return value;
	}

	@Override
	public double actOn(Turtle myTurtle, int index) {
		double distance = Math.hypot(myTurtle.getXPosition(), myTurtle.getYPosition());
		myTurtle.moveTo(0, 0);
		myTurtle.clear();
		myTurtle.setRotation(90);
		return distance;
	}
}