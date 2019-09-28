package model.commands.regular_operations;

import model.commands.Command;

/**
 * @author jeffreyli andrew
 *         returns 1 if arguments are equal, otherwise 0
 *         
 */
public class Equal extends Command {

	public Equal() {
		super(2);
	}
	@Override
	public double run() {
		double check = act(0);
		for(int x = 1; x < getChildren().size(); x ++) {
			if(check != act(x))
				return 0;
		}
		return 1;
	}

	@Override
	public double act(int index) {
		return childValue(index);
	}
}
