package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * returns tangent of degrees
 */
public class Tangent extends Command {

	public Tangent() {
		super(1);
	}

	@Override
	public double act(int index) {
		double value = childValue(index);
		return (Math.tan(Math.toRadians(value)));
	}
}
