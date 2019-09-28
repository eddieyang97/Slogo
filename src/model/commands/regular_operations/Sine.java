package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * returns sine of degrees
 * Corrects for java imprecision at angles of 2*n*pi
 */
public class Sine extends Command {

	public Sine() {
		super(1);
	}

	@Override
	public double act(int index) {
		double value = childValue(index);
		if(value % 180 == 0)
			return 0;
		return Math.sin(Math.toRadians(value));
	}
}
