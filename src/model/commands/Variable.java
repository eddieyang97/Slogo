package model.commands;

import model.SLogoException;
/**
 * Stores a variable value that can be edited
 */
public class Variable extends Command {
	private Double value;
	private String name;
	/**
	 * Creates a new Variable with the corresponding name
	 * @param s
	 */
	public Variable(String s) {
		super(0);
		name = s;
	}

	/**
	 * Sets the value of the variable value
	 * @param d
	 */
	public void setValue(double d) {
		value = d;
	}
	/**
	 * Returns the value of the variable
	 * @throws exception if the variable has an undefined value
	 */
	@Override
	public double act(int index) {
		if(value == null || value.equals(Double.NaN)) {
			throw new SLogoException("Undefined variable " + name + ".");
		}
		return value;
	}
	/**
	 * Returns the name of this variable
	 * @return the name associated with the Variable
	 */
	public String getName() {
		return name;
	}
}
