package model.commands;
/**
 * Represents a series of sequential commands enclosed by brackets
 */
import java.util.List;

public class ListCommand extends Command {
	public ListCommand() {
		super(0);
	}
	public ListCommand(List<Command> commands) {
		this();
		setChildren(commands);
	}
	@Override
	public double run() {
		double answer = 0;
		for(int x = 0; x < getChildren().size(); x++) {
			answer = act(x);
		}
		return answer;
	}
	@Override
	public double act(int index) {
		return childValue(index);
	}

	@Override
	public int howManyArguments() {
		return getChildren().size();
	}
	/**
	 * Sets the children of this command to the children of the input command
	 * @param n the command whose children will be replacing this command's children
	 */
	public void overwrite(Command n) {
		setChildren(n.getChildren());
	}

}
