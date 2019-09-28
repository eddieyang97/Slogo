package model.commands.turtle_commands;

import model.SLogoException;
import model.Turtle;
import model.TurtleTracker;
/**
 * 
 * @author jeffreyli
 * sets shape to the shape at index of palette
 */
public class SetShape extends TurtleAction{

	public SetShape(TurtleTracker t) {
		super(t, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double actOn(Turtle t, int index) {
		int answer = (int)childValue(index);
		try {
			t.setShape(answer);
		}
		catch(Exception e){
			throw new SLogoException("Invalid shape index specified");
		}
		return answer;
	}

}
