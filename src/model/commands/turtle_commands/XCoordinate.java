package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * returns the turtle's X coordinate from the center of the screen
 */
public class XCoordinate extends TurtleAction{
	
	public XCoordinate(TurtleTracker t) {
		super(t, 0);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		return myTurtle.getXPosition();
	}

}
