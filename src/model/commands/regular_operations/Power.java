package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * returns base raised to the power of the exponent
 */
public class Power extends Command {

	public Power() {
		super(2);
	}

	@Override
	public double act(int index) {
		// TODO Auto-generated method stub
		double base = childValue(index);
		double exponent = childValue(index + 1);
		return Math.pow(base, exponent);
	}
}
