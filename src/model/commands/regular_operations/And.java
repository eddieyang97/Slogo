package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli andrew
 *         returns 1 if test1 and test2 are non-zero, otherwise 0.
 *         short-circuits.
 */
public class And extends Command {

	public And() {
		super(2);
	}
	@Override
	public double run() {
		for(int x = 0; x < getChildren().size(); x++) {
			if(act(x) == 0)
				return 0;
		}
		return 1;
	}

	@Override
	public double act(int index) {
		return (childValue(index) != 0)? 1:0;
	}
}
