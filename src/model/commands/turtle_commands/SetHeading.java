package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * turns turtle to an absolute heading
 * returns number of degrees moved
 */
public class SetHeading extends TurtleAction {
	public static final double NUM_DEGREES = 360;
	public SetHeading(TurtleTracker t) {
		super(t, 1);
	}

	@Override
	public double actOn(Turtle myTurtle, int index) {
			double value = childValue(index) % NUM_DEGREES;
			if(value < 0)
				value += NUM_DEGREES;
			double toReturn = value - myTurtle.getRotationProperty().get();
			myTurtle.rotate(toReturn);
			return Math.min(Math.abs(toReturn),NUM_DEGREES - Math.abs(toReturn));
	}
}