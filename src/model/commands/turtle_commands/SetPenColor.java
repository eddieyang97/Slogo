package model.commands.turtle_commands;

import model.SLogoException;
import model.Turtle;
import model.TurtleTracker;
/**
 * 
 * @author jeffreyli
 * sets pencolour to the colour at index of palette
 */
public class SetPenColor extends TurtleAction{

	public SetPenColor(TurtleTracker t) {
		super(t, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double actOn(Turtle t, int index) {
		int answer = (int)childValue(index);
		try {
			t.setPenColor(answer);
		}
		catch(Exception e) {
			throw new SLogoException("Invalid pen color index specified");
		}
		return answer;
	}

}
