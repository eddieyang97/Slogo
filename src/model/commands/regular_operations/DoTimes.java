package model.commands.regular_operations;

import model.SLogoException;
import model.commands.Command;
import model.commands.ListCommand;
import model.commands.Variable;
/**
 * Implements the DoTimes command
 * @author andrew
 *
 */
public class DoTimes extends Command {
	public DoTimes(){
		super(2);
	}
	@Override
	public double act(int index) {

		if(!(getChildren().get(index) instanceof ListCommand))
			throw new SLogoException("Badly defined doTimes loop");
		ListCommand varSpecs = (ListCommand) getChildren().get(index);
		Variable var;
		try {
			var = (Variable) varSpecs.getChild(0);	
		}
		catch(Exception e) {
			throw new SLogoException("Badly defined variable specifications in doTimes loop");
		}
		double limit = varSpecs.childValue(1);
		var.setValue(1);
		double answer = 0;
		while(var.run() <= limit) {
			answer = childValue(index+1);
			var.setValue(var.run() + 1);
		}
		return answer;
	}

}
