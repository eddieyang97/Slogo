package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli returns 1 if the value of expr1 is strictly less than the
 *         returns 1 if test1 or test2 are non-zero, otherwise 0
 */
public class Or extends Command {

	public Or() {
		super(2);
	}
	@Override
	public double run() {
		for(int x = 0; x < getChildren().size(); x++) {
			if(act(x) != 0)
				return 1;
		}
		return 0;
	}

	@Override
	public double act(int index) {
		return (childValue(index) != 0) ? 1:0;
	}
}
