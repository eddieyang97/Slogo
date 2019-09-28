package model.commands.regular_operations;
import model.commands.Command;
/**
 * Implements the if command
 * @author andrew
 *
 */
public class If extends Command {
	public If() {
		super(2);
	}
	@Override
	public double act(int index) {

		return (childValue(index) == 0)? 0:childValue(index+1);
	}

}
