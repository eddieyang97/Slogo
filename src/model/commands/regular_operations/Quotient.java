package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli returns quotient of the values of expr1 and product of all other values
 */
public class Quotient extends Command {

	public Quotient() {
		super(2);
	}

	@Override
	public double run() {
		double answer = act(0);
		for(int x = 1; x < getChildren().size(); x ++) {
			answer /= act(x);
		}
		return answer;
	}
	@Override
	public double act(int index) {
		return childValue(index);
	}

}
