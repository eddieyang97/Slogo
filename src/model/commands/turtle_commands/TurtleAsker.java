package model.commands.turtle_commands;
/**
 * Defines a subclass of Turtle commands that temporarily asks some Turtles to do something, then restores which turtles were active
 * @author andrew
 */
import model.TurtleTracker;
import model.commands.Command;
import model.commands.Constant;
import model.commands.ListCommand;

public abstract class TurtleAsker extends TurtleCommand {

	public TurtleAsker(TurtleTracker t) {
		super(t, 2);
	}

	@Override
	public double act(int index) {
		int originalIndex = getTurtles().getIndex();
		ListCommand saved = saveOriginalTurtles();
		

		Tell tell = new Tell(getTurtles());
		tell.addChild(getListOfTurtles(index));
		tell.run();
		
		double answer = runCommandsOnTurtles(index);
		
		Tell reset = new Tell(getTurtles());
		reset.addChild(saved);
		reset.run();
		getTurtles().setIndex(originalIndex);
		return answer;
	}
	/**
	 * Returns a ListCommand containing Constants corresponding to all currently active turtles
	 * @return ListCommand of original turtles
	 */
	private ListCommand saveOriginalTurtles() {
		ListCommand originals = new ListCommand();
		for(int x = 0; x < getTurtles().getSize(); x++)
			if(getTurtles().get(x).isActive())
				originals.addChild(new Constant(x+1));
		return originals;
	}
	/**
	 * Runs commands on each turtle such that 1 turtle completes the commands before the next one starts.
	 * @param index offset of arguments
	 * @return double result of running the commands
	 */
	private double runCommandsOnTurtles(int index) {
		double value = 0;
		for (int x = 0; x < getTurtles().getSize(); x++) {
			if (getTurtles().get(x).isActive()) {
				getTurtles().setIndex(x);
				value = childValue(index + 1);
			}
		} 
		return value;
	}
	/**
	 * Returns a ListCommand containing Constants that correspond to all Turtles that should be temporarily activated
	 * @param index offset of arguments
	 * @return turtles to be set
	 */
	protected abstract Command getListOfTurtles(int index);
}
