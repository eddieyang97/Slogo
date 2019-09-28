package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli returns sum of the values of expr1 and expr2
 * Overrides grouping behavior to sum all expressions
 */
public class Sum extends Command {
	public Sum() {
		super(2);
	}
	@Override
	public double run() {
		double answer = 0;
		for(int x = 0; x < getChildren().size(); x ++) {
			answer += act(x);
		}
		return answer;
	}
	@Override
	public double act(int index) {
		return childValue(index);
	}
 
}
