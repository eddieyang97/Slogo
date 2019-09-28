package model.commands.regular_operations;
/**
 * For loop implementation
 * @author andrew
 */
import model.SLogoException;
import model.commands.Command;
import model.commands.ListCommand;
import model.commands.Variable;

public class For extends Command{
	public For() {
		super(2);
	}
	@Override
	public double act(int index) {
		if(!(getChildren().get(index) instanceof ListCommand))
			throw new SLogoException("Badly defined for loop.");
		ListCommand varSpecs = (ListCommand) getChild(index);
		Variable var;
		double first;
		double last;
		double increment;
		try {
			var = (Variable) varSpecs.getChild(0);
			first = varSpecs.childValue(1);
			last = varSpecs.childValue(2);
			increment = varSpecs.childValue(3);
		}
		catch(IndexOutOfBoundsException e)
		{
			throw new SLogoException("Too few variable specifications in for loop");
		}
		catch(ClassCastException e){
			throw new SLogoException("Badly defined for loop");
		}
		var.setValue(first);
		double answer = 0;
		while(var.run() <= last) {
			answer = childValue(index+1);
			var.setValue(var.run() + increment);
		}
		return answer;
	}

}
