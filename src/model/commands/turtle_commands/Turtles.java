package model.commands.turtle_commands;

import model.TurtleTracker;

public class Turtles extends TurtleCommand{

	public Turtles(TurtleTracker t) {
		super(t, 0);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double act(int index) {
		return getTurtles().getSize();
	}


}
