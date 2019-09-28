package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * returns cosine of degree
 * Corrects for java imprecision at angles of (1+2n)*pi
 * ç
 */
public class Cosine extends Command {

	public Cosine() {
		super(1);
	}

	@Override
	public double act(int index) {
		double value = childValue(index);
		if((value-90) % 180 == 0)
			return 0;
		return Math.cos(Math.toRadians(value));
	}
}
