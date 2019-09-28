package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

public class GetShape extends TurtleAction {

	public GetShape(TurtleTracker t) {
		super(t, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double actOn(Turtle t, int index) {
		return t.getShapeIndex();
	}

}
