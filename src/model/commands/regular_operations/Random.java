package model.commands.regular_operations;

import model.SLogoException;
import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * returns random non-negative number strictly less than max
 */
public class Random extends Command {

	public Random() {
		super(1);
	}
	@Override
	public double act(int index) {
		double value = childValue(index);
		if(value < 0)
			throw new SLogoException("Random can only be called on nonnegative numbers!");
		return Math.random() * value;
	}
}
