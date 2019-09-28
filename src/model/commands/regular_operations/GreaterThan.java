package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli andrew
 *         returns 1 if the value of expr1 is strictly greater than the value of expr2, otherwise 0
 */
public class GreaterThan extends Command {

	public GreaterThan() {
		super(2);
	}

	@Override
	public double act(int index) {
		return (childValue(index) > childValue(index+1))? 1 : 0;
	}
}
