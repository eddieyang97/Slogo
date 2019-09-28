package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 *         returns 1 if the value of expr1 and the value of expr2 are not equal, otherwise 0
 */
public class NotEqual extends Command {

	public NotEqual() {
		super(2);
	}

	@Override
	public double run() {
		double check = act(0);
		for(int x = 1; x < getChildren().size(); x ++) {
			if(check != act(x))
				return 1;
		}
		return 0;
	}

	@Override
	public double act(int index) {
		return childValue(index);
	}
}
