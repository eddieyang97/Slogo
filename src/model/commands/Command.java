package model.commands;

import java.util.ArrayList;
import java.util.List;

import model.SLogoException;

/**
 * Superclass for all possible methods
 */
public abstract class Command {	
	private int defaultNumberOfArguments;
	private List<Command> children;
	

	public Command(int numVars) {
		children = new ArrayList<Command>();
		defaultNumberOfArguments = numVars;
	}
	/**
	 * Checks that the Command is properly constructed, then runs the Command in a way that accounts for grouping
	 * @return the result of calling the command
	 */
	public double run() {
		exceptionCheck();
		if(howManyArguments() == 0)
			return act(0);
		double answer = 0;
		for(int x = 0; x < children.size(); x += howManyArguments()) {
			answer = act(x);
		}
		return answer;
	}
	/**
	 * Checks for grouping-related exceptions
	 */
	public void exceptionCheck() {
		if(howManyArguments() != 0 && (children.size() % howManyArguments() != 0 || children.size() == 0)) {
			throw new SLogoException("Invalid number of arguments from grouping");
		}
	}
	
	/**
	 * Command performs desired action based on arguments starting at the index parameter
	 * @param The beginning index of the parameters. Can be ignored as necessary
	 */
	public abstract double act(int index);
	
	/**
	 * returns how many command variables the command needs to take in
	 */
	public int howManyArguments() {
		return defaultNumberOfArguments;
	}
	/**
	 * Returns the result of calling run() on the child at the desired index
	 * @param index
	 * @return
	 */
	public double childValue(int index) {
		return children.get(index).run();
	}
	
	/** 
	 * adds a child to the commands children
	 */
	public void addChild(Command child) {
		children.add(child);
	}
	/**
	 * Returns the child at the particular Index
	 * @param index index of the child
	 * @return the Command child
	 */
	public Command getChild(int index) {
		return children.get(index);
	}
	/**
	 * Returns the List of children commands.
	 * @return the list of children commands
	 */
	protected List<Command> getChildren(){
		return children;
	}
	/**
	 * Sets the children to equal input new List of Commands
	 * @param newChildren the command's new children
	 */
	protected void setChildren(List<Command> newChildren) {
		children = newChildren;
	}
}
