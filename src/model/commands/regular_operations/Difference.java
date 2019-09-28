package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author andrew jeffreyli
 * returns difference of the values of expr1 and all subsequent arguments
 */
public class Difference extends Command {

	public Difference() {
		super(2);
	}

	@Override
	public double run() {
		double answer = act(0);
		for(int x = 1; x < getChildren().size(); x ++) {
			answer -= act(x);
		}
		return answer;
	}
	@Override
	public double act(int index) {
		return childValue(index);
	}
}
