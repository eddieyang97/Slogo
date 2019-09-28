package model.commands;



import model.commands.Command;

/**
 * returns constant
 */
public class Constant extends Command {
	private double value;

	public Constant(double v1) {
		super(0);
		value = v1;
	}

	@Override
	public double act(int index) {
		return value;
	}
}
