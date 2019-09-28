package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * returns natural log of expr
 */
public class NaturalLog extends Command {

	public NaturalLog() {
		super(1);
	}

	@Override
	public double act(int index) {
		double value = childValue(index);
		return Math.log(value);
	}
}
