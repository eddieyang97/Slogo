package model;
/**
 * Wraps an ArrayList and a manually mutable index into an object
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import view.Visualizer;

public class TurtleTracker {
	private List<Turtle> turtles;
	private int myIndex;
	private Visualizer myVis;
	public TurtleTracker(Visualizer vis) {
		turtles = new ArrayList<>();
		myIndex = -1;
		myVis = vis;
	}
	/**
	 * Returns the current index being looked at by the commands
	 * @return -1 if not being looped over, the correct index otherwise.
	 */
	public int getIndex() {
		return myIndex;
	}
	/**
	 * Set the index to the given value
	 * @param activeTurtle the new index
	 */
	public void setIndex(int activeTurtle) {
		myIndex = activeTurtle;
	}
	/**
	 * Gets the Turtle at the current index
	 * @return the Turtle currently being acted on
	 */
	public Turtle getTurtle() {
		return get(myIndex);
	}
	/**
	 * Returns the Turtle at the given index
	 * @param index the place in the TurtleTracker
	 * @return the corresponding Turtle
	 */
	public Turtle get(int index) {
		return turtles.get(index);
	}
	public void addTurtle(Turtle t) {
		turtles.add(t);
		myVis.addTurtle((TurtleInterface) t);
	}
	public void add() {
		addTurtle(new Turtle(myVis,getSize() + 1));
	}
	/** 
	 * Returns the total number of turtles
	 * @return the total number of turtles
	 */
	public int getSize() {
		return turtles.size();
	}
	/**
	 * Activates all turtles with ID's in the given set, deactivates all other turtles
	 * @return the ID of the last activated Turtle, -1 if no turtle is activated
	 */
	public double setActiveTurtles(Set<Double> actives) {
		double value = -1;
		for(int y = 0; y < getSize(); y++) {
			if(actives.contains(y + 1.0)) {
				value = get(y).getID();
				get(y).setActive(true);
			}
			else
				get(y).setActive(false);
		}
		setIndex(-1);
		return value;
	}
}
