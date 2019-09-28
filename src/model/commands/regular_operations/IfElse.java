package model.commands.regular_operations;
import model.commands.Command;
/**
 * Implements if/else statement
 * @author andrew
 *
 */
public class IfElse extends Command {
	public IfElse() {
		super(3);
	}
	@Override
	public double act(int index) {
		return(childValue(index) != 0)? childValue(index + 1) : childValue(index + 2);
	}
}
