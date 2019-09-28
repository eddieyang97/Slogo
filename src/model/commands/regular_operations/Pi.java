package model.commands.regular_operations;

import model.commands.Command;

/**
 * 
 * @author jeffreyli 
 * reports the number pi
 */
public class Pi extends Command {

	public Pi() {
		super(0);
	}

	@Override
	public double act(int index) {
		return Math.PI;
	}
}
