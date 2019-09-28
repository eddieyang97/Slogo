package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;

public interface TurtleInterface {
	
	/**
	 * moves turtle to specified position, and draws a line if necessary.
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 */
	public void moveTo(double x, double y);
	
	/**
	 * toggles whether a turtle is active or not
	 */
	public void toggleActive();
	
	/**
	 * sets the pen width to value
	 * @param value
	 */
	public void setPenWidth(double value);
	
	/**
	 * 
	 * @return x position of turtle
	 */
	public double getXPosition();
	/**
	 * 
	 * @return y position of turtle
	 */
	public double getYPosition();
	
	/**
	 * 
	 * @return ID of turtle
	 */
	public double getID();
	
	/**
	 * 
	 * @return shape's index in the image palette
	 */
	public ImageView getShape();
	
	/**
	 * 
	 * @return rotation of turtle as a double property
	 */
	public DoubleProperty getRotationProperty();
	
	/**
	 * 
	 * @return pend width of turtle as a property
	 */
	public DoubleProperty getPenWidthProperty();
	
	/**
	 * 
	 * @return property of whether pen is down 
	 */
	public BooleanProperty getPenDownProperty();
	
	/**
	 * 
	 * @return property of whether pen is active
	 */
	public BooleanProperty getActiveProperty();
	public DoubleProperty getTrueRotationProperty();
	public DoubleProperty getShapeXProperty();
	public DoubleProperty getShapeYProperty();
}
