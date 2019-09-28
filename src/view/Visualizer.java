package view;

import javafx.beans.property.BooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.TurtleInterface;

/**
 * External API for Visualizer; for the model to use
 *
 */
public interface Visualizer {
	
	/**
	 * draw a line between the two points (x1, y1), (x2, y2)
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param penWidth 
	 * @param color 
	 */
	public void drawLine(double x1, double y1, double x2, double y2, Color color, double penWidth);
	
	/**
	 * clears all lines and resets the turtle position to (0, 0)
	 */
	public void reset();
	
	/**
	 * adds a variable to the variable display
	 * @param var
	 * @param val
	 */
	public void addVariable(String var, double val);
	
	/**
	 * removes a variable from the variable display
	 * @param var
	 * @param val
	 */
	public void removeVariable(String var, double val);
	
	/**
	 * adds a function to the function display
	 * @param name
	 */
	public void addFunction(String name);
	
	/**
	 * removes a function from the function display
	 * @param name
	 */
	public void removeFunction(String name);

	/**
	 * adds a turtle to be displayed
	 * @param t
	 */
	public void addTurtle(TurtleInterface t);

	
	/**
	 * Sets background colour to the specified index
	 * @param index of palette
	 */
	public void setBGColor(double index);
	
	/**
	 * sets palette index to color
	 * @param index
	 * @param color
	 */
	public void setPalette(double index, Color color);
	
	/**
	 * 
	 * @param index
	 * @return colour at index of palette
	 */
	public Color getColorIndex(int index);
	
	/**
	 * 
	 * @param index
	 * @return Image at index in image palette
	 */
	public Image getShapeIndex(int index);
	
	/**
	 * returns the height of the backgrounds
	 * @return
	 */
	public double getBGHeight();

	/**
	 * returns the width of the background
	 * @return
	 */
	public double getBGWidth();
	
	/**
	 * returns whether animation is running
	 */
	public boolean getAnimate();
	
	/**
	 * returns the speed of the animation
	 * @return
	 */
	public int getSpeed();
	
	/**
	 * returns the paused property
	 * @return
	 */
	public BooleanProperty getPauseProperty();
}
