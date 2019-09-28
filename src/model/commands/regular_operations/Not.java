package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli andrew
 * 	returns 1 if the value of expr1 is strictly less than the
 *         returns 1 if test1 or test2 are non-zero, otherwise 0
 */
public class Not extends Command {

	public Not() {
		super(1);
	}

	@Override
	public double act(int index) {
		return (childValue(index) == 0) ? 1 : 0;
	}
}
