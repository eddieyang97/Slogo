package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * returns negative of the values of expr
 */
public class Minus extends Command {

	public Minus() {
		super(1);
	}

	@Override
	public double act(int index) {
		return childValue(index) * -1;
	}
}
