package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * returns arctangent of degrees
 */
public class ArcTangent extends Command {

	public ArcTangent() {
		super(1);
	}

	@Override
	public double act(int index) {
		double value = childValue(index);
		return Math.toDegrees(Math.atan(value));
	}
}
