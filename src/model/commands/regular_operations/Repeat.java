package model.commands.regular_operations;

import model.SLogoException;
import model.commands.Command;
import model.commands.Variable;
/**
 * The repeat command in SLogo.
 * @author andrew
 */
public class Repeat extends Command {

	public static final String DEFAULT_VAR_NAME = ":repcount";

	public Repeat() {
		super(1);
	}
	@Override
	public void exceptionCheck() {
		if(getChildren().size() % 3 != 0 || getChildren().size() == 0) {
			throw new SLogoException("Invalid number of arguments from grouping");
		}
	}
	@Override
	public double run() {
		exceptionCheck();
		if(howManyArguments() == 0)
			return act(0);
		double answer = 0;
		for(int x = 0; x < getChildren().size(); x += 3) {
			answer = act(x);
		}
		return answer;
	}
	
	@Override
	public double act(int index) {
		Variable v;
		try {
			v = (Variable)getChild(index);
		}
		catch(Exception e) {
			throw new SLogoException("Error occured when defining Repeat loop");
		}
		Double storedValue;
		try {
			storedValue = v.run();
		}
		catch(SLogoException e) {storedValue = Double.NaN;}
		double numTimes = Math.round(childValue(index + 1));
		double val = 0;
		for(int x = 1; x <= numTimes; x++) {
			v.setValue(x);
			val = childValue(index + 2);
		}
		v.setValue(storedValue);
		return val;
	}

}
