package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli returns sum of the values of expr1 and expr2
 */
public class Remainder extends Command {

	public Remainder() {
		super(2);
	}

	@Override
	public double act(int index) {
		return childValue(index) % childValue(index + 1);
	}
}
