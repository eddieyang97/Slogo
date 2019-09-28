package model.commands;
/**
 * The Command corresponding to a user-defined function
 */
import java.util.ArrayList;
import java.util.List;

import model.SLogoException;

public class Function extends Command {
	private static final int ARG_SIZE = 2;
	private String name;
	public Function(String s) {
		super(0);
		name = s;
	}
	/**
	 * Creates a function with the same parameters and methods as the input function
	 * @param f
	 */
	public Function(Function f) {
		this(f.name);
		if(f.howManyArguments() < 0)
			throw new SLogoException("Attempted to reference undefined function " + this.name + ".");
		for(int a = 0; a < 2; a++) {
			getChildren().add(f.getChild(a));
		}
	}
	@Override
	public void exceptionCheck() {
		if (howManyArguments() < 0)
			throw new SLogoException("Undefined function " + this.name + ".");
		if(howManyArguments() != 0 && (getChildren().size() == ARG_SIZE 
				|| (getChildren().size() - ARG_SIZE) % howManyArguments() != 0))
			throw new SLogoException("Invalid number of arguments from grouping");
		if(!correctCheck())
			throw new SLogoException("Poorly defined function.");

	}
	@Override
	public double run() {
		exceptionCheck();
		if(howManyArguments() == 0)
			return act(0);
		double answer = 0;
		for(int x = ARG_SIZE; x < getChildren().size(); x += howManyArguments()) {
			answer = act(x);
		}
		return answer;
	}
	@Override
	public double act(int index) {
		List<Double> originalVals = storeOriginalVarValues();
		for(int x = 0; x < howManyArguments(); x++) {
			try {
				Variable var = (Variable)(getChild(0).getChild(x));
				var.setValue(getChild(index + x).run());
			}
			catch(Exception e) {
				throw new SLogoException("Poorly defined variables in function " + name + ".");
			}
		}
		double value = getChildren().get(1).run();
		for(int x = 0; x < howManyArguments(); x++) 
			((Variable)(getChild(0).getChild(x))).setValue(originalVals.get(x));
		return value;
	}
	/**
	 * Stores the original values of variables so that they can be restored at the end of the function call
	 * @return originalVals the list of all original values in order.
	 */
	private List<Double> storeOriginalVarValues() {
		List<Double> originalVals = new ArrayList<>();
		for(int x = 0; x < howManyArguments(); x++) {
			try {
				Variable var = (Variable)(getChild(0).getChild(x));
				try {
					originalVals.add(var.run());
				}
				catch(SLogoException e) { originalVals.add(Double.NaN);}
			}
			catch(Exception e) {
				throw new SLogoException("Poorly defined variables in function " + this.name + ".");
			}
		}
		return originalVals;
	}
	/**
	 * Overrides the howManyVariables function to return the number of parameters needed.
	 */
	@Override
	public int howManyArguments() {
		if(getChildren().size() > 0) {
			return getChild(0).howManyArguments();
		}
		return -1;
	}
	/**
	 * Checks whether the function is correctly defined
	 * @return true if function is valid; false otherwise
	 */
	public boolean correctCheck() {
		if(!(getChildren().get(0) instanceof ListCommand) || (getChildren().size() >=1 && !(getChildren().get(1) instanceof ListCommand)))
			return false;
		for(int a = 0; a < getChild(0).getChildren().size(); a++) {
			if(!(getChild(0).getChild(a) instanceof Variable))
				return false;
		}
		return true;
	}
	/**
	 * Intermediate step; adds or replaces the parameters for a function 
	 * @param c the new parameters
	 */
	public void replaceArg(Command c) {
		ListCommand filler = new ListCommand();
		if(!(c instanceof ListCommand))
			throw new SLogoException("Attempting to define function with invalid parameters.");
		if(getChildren().size() >= 2) {
			((ListCommand)getChildren().get(0)).overwrite(c);
			((ListCommand)getChildren().get(1)).overwrite(filler);
		}
		else {
			getChildren().add(c);
			getChildren().add(filler);
		}
	}
	/**
	 * Returns the name associated with the function
	 * @return the function's name
	 */
	public String getName() {
		return name;
	}
}
