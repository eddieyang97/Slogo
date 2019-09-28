package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli turns turtle to face the point (x, y), where (0, 0) is the
 *         center of the screen returns the number of degrees turtle turned
 */
public class SetTowards extends TurtleAction {


	public SetTowards(TurtleTracker t) {
		super(t, 2);
	}

	@Override
	public double actOn(Turtle myTurtle, int index) {
		double difference = 0;
		double x2 = childValue(index);
		double y2 = childValue(index + 1);
		double x1 = myTurtle.getXPosition();
		double y1 = myTurtle.getYPosition();

		double angle = Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI ;
		if(angle < 0)
			angle += 360;

		difference = angle - myTurtle.getRotationProperty().get();
		myTurtle.rotate(difference);			
		return Math.min(Math.abs(difference), 360 - Math.abs(difference));
	}
}