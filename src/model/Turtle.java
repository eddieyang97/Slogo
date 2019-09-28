package model;

import view.Visualizer;

import java.util.LinkedList;
import java.util.Queue;


import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Turtle object which responds to user commands
 */
public class Turtle implements TurtleInterface {
    
	public static final String T1 = "turt.gif";
	public static final String T2 = "turtle.png";
	
	private BooleanProperty penDown;
	private boolean visible;
	private BooleanProperty active;
	private Visualizer visualizer;
	private ImageView shape;
	private int ID;
	private DoubleProperty penWidth;
	private Color color;
	private int colorIndex;
	private int shapeIndex;
	private static final double DEFAULT_PENWIDTH = 1.0;
	private Queue<Animation> playQueue;
	private double currentX = 0;
	private double currentY = 0;
	private double currentA = 90;
	private Animation currentAnim = null;
	private boolean running;
	private boolean paused;

	public Turtle(double x, double y, double r, Visualizer v, Image turtleImg, int id) {
		visualizer = v;
		playQueue = new LinkedList<>();
		running = false;
		paused = false;
		shape = new ImageView(turtleImg);
        shape.setOnMouseDragged(e -> {
        	shape.setX(e.getSceneX() - v.getBGWidth()/2);
        	shape.setY(e.getSceneY() - v.getBGHeight()/2 - 2 * shape.getFitHeight());
        	currentX = shape.getX();
        	currentY = -shape.getY();
        });
		setRotation(r);
		visible = true;
		ID = id;
		color = Color.BLACK;
		penWidth = new SimpleDoubleProperty(DEFAULT_PENWIDTH);
		active = new SimpleBooleanProperty(true);
		colorIndex = 0;
		shapeIndex = 0;
		penDown = new SimpleBooleanProperty(false);
		setRotation(r);
		visualizer.getPauseProperty().addListener((o, ol, n) -> {
			paused = n;
			if(paused) {
				pause();
			} else {
				play();
			}
		});

	}
	/**
	 * Constructs a Turtle with default parameters
	 * @param v the Visualizer associated with the Turtle
	 * @param ID the ID number of Turtles to be created
	 */
	public Turtle(Visualizer v, int ID) {
		this(0, 0, 90, v, new Image(Turtle.class.getClassLoader().getResourceAsStream(T2)), ID);
	}

	/**
	 * moves turtle forward
	 * implemented to limit the impact of Java imprecision where outputs of trigonometric functions should be 0 
	 * @param value to move by
	 */
	public void moveForward(double value) {
		double radianAngle = currentA/180*Math.PI;
		double newxPosition;
		if((currentA-90) % 180 != 0) {
			newxPosition = currentX + value * Math.cos(radianAngle);
		} else {
			newxPosition = currentX;
		}
		double newyPosition;
		if(currentA % 180 != 0) {
			newyPosition = currentY + value * Math.sin(radianAngle);
		} else { 
			newyPosition = currentY;
		}
		moveTo(newxPosition, newyPosition);
	}
	/**
	 * rotates turtle counterclockwise
	 * 
	 * @param value to rotate by
	 */
	public void rotate(double value) {
		setRotation(currentA + value);
		while (currentA >= 360) {
			setRotation(currentA - 360);
		}
		while (currentA < 0) {
			setRotation(currentA + 360);
		}
	}

	/**
	 * moves turtle to specified position, and draws a line if necessary.
	 * 
	 * @param x
	 *            coordinate
	 * @param y
	 *            coordinate
	 */
	public void moveTo(double x, double y) {
		if (penDown.getValue()) {
			visualizer.drawLine(currentX, currentY, x, y, color, penWidth.get());
		}
		
		if(visualizer.getAnimate()) {
			TranslateTransition pt = new TranslateTransition(new Duration(visualizer.getSpeed()), shape);
			pt.setToX(x);
			pt.setToY(-y);
			playQueue.add(pt);
			if(!paused) {
				runAnimation();
			}
		} else {
			stopAnimation();
			shape.setX(x);
			shape.setY(-y);
		}
		currentX = x;
		currentY = y;
	}
	
	private void stopAnimation() {
		if(currentAnim != null) {
			currentAnim.stop();
			currentAnim = null;
			playQueue.clear();
		}
	}
	
	
	private void runAnimation() {
		if(!running && playQueue.size() > 0) {
			running = true;
			SequentialTransition st = new SequentialTransition(shape, playQueue.toArray(new Animation[playQueue.size()]));
			playQueue.clear();
			currentAnim = st;
			st.play();
			st.setOnFinished(e -> {
				running = false;
				currentAnim = null;
				runAnimation();
			});
		}
	}

	private void pause() {
		if(currentAnim != null) {
			currentAnim.pause();
		}
	}
	
	private void play() {
		if(currentAnim != null) {
			currentAnim.play();
		}
	}
	
	public void penUp() {
		penDown.setValue(false);
	}
	
	/**
	 * puts pen of turtle down
	 */
	public void penDown() {
		penDown.setValue(true);
	}
	
	/**
	 * makes turtle invisible
	 */
	public void makeInvisible() {
		visible = false;
		shape.setOpacity(0);
	}

	public void makeVisible() {
		visible = true;
		shape.setOpacity(1);
	}

	public BooleanProperty getPenDownProperty() {
		return penDown;
	}

	public boolean isActive() {
		return active.getValue();
	}
	
	public BooleanProperty getActiveProperty() {
		return active;
	}

	public boolean isShowing() {
		return visible;
	}

	public DoubleProperty getRotationProperty() {
		return new SimpleDoubleProperty(-shape.rotateProperty().get());
	}

	public DoubleProperty getTrueRotationProperty() {
		return shape.rotateProperty();
	}
	
	public double getXPosition() {
		return shape.getX();
	}

	public double getYPosition() {
		return -shape.getY();
	}

	public double getID() {
		return ID;
	}

	public void setRotation(double value) {
		if(visualizer.getAnimate()) {
			RotateTransition rt = new RotateTransition(new Duration(visualizer.getSpeed()), shape);
			rt.setToAngle(-value);
			currentA = value;
			playQueue.add(rt);
			if(!paused) {
				runAnimation();
			}
		} else {
			stopAnimation();
			shape.setRotate(-value);
		}
		
	currentA = value;

	}
	
	
	/**
	 * clears the screen on the visualizer
	 */
	public void clear() {
		visualizer.reset();
	}

	@Override
	public void toggleActive() { 
		setActive(!active.get());
	}

	/**
	 * sets whether turtle is active or not, and changes color of turtle accordingly
	 * @param b
	 */
	public void setActive(boolean b) {
		active.set(b);
		if(active.get()) {
			shape.setEffect(new ColorAdjust(0, 0, 0, 0));
		} else {
			shape.setEffect(new ColorAdjust(0, 0, -0.5, 0));
		}
	}

	@Override
	public void setPenWidth(double value) {
		penWidth.set(value);
	}

	public void setPenColor(int index) {
		Color potentialcolor = visualizer.getColorIndex(index);
		if(potentialcolor == null)
			throw new SLogoException("Invalid index for getting pen color");
		color = potentialcolor;
		colorIndex = index;
	}

	public double getColorIndex() {
		return colorIndex;
	}
	
	public double getShapeIndex() {
		return shapeIndex;
	}
	
	public void setShape(int index) {
		shape.setImage(visualizer.getShapeIndex(index));
		shapeIndex = index;
	}
	
	public ImageView getShape() {
		return shape;
	}
	
	@Override
	public DoubleProperty getPenWidthProperty() {
		// TODO Auto-generated method stub
		return penWidth;
	}
	
	public DoubleProperty getShapeXProperty() {
		return shape.xProperty();
	}
	
	public DoubleProperty getShapeYProperty() {
		return shape.yProperty();
	}

}
