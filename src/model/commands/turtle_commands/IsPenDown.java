package model.commands.turtle_commands;

import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 * returns 1 if turtle's pen is down, 0 if it is up
 */
public class IsPenDown extends TurtleAction{
	
	public IsPenDown(TurtleTracker t) {
		super(t, 0);
	}
	
	@Override
	public double actOn(Turtle myTurtle, int index) {
		return (myTurtle.getPenDownProperty().getValue()) ? 1:0;
	}

}
