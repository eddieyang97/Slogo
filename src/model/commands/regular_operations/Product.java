package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli returns product of the values of expr1 and expr2
 */
public class Product extends Command {

	public Product() {
		super(2);
	}
	@Override
	public double run() {
		double answer = 1;
		for(int x = 0; x < getChildren().size(); x ++) {
			answer *= act(x);
		}
		return answer;
	}
	@Override
	public double act(int index) {
		return childValue(index);
	}
}
