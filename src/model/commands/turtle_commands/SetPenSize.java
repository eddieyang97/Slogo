package model.commands.turtle_commands;

import model.SLogoException;
import model.Turtle;
import model.TurtleTracker;

/**
 * 
 * @author jeffreyli
 *	changes size of pen for the turtles
 */
public class SetPenSize extends TurtleAction{

	public SetPenSize(TurtleTracker t) {
		super(t, 1);
	}

	@Override
	public double actOn(Turtle t, int index) {
		double answer = childValue(index);
		if(answer < 0)
			throw new SLogoException("attempted to define negative pen width");
		t.setPenWidth(answer);
		return answer;
	}

}
