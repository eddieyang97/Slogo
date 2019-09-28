package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * returns the turtle's heading in degrees
 */
public class Heading extends TurtleAction{
	
	public Heading(TurtleTracker t) {
		super(t, 0);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		return myTurtle.getRotationProperty().get();
	}

}
