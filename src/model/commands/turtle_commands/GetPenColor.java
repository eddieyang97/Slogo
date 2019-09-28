package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * returns pen color index
 * @author jeffreyli
 *
 */
public class GetPenColor extends TurtleAction {

	public GetPenColor(TurtleTracker t) {
		super(t, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double actOn(Turtle t, int index) {
		return t.getColorIndex();
	}

}
