package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * puts pen down such that when the turtle moves, it leaves a trail
 * returns 1
 */
public class PenDown extends TurtleAction {
	
	public PenDown(TurtleTracker t) {
		super(t, 0);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		myTurtle.penDown();
		return 1;
	}

}