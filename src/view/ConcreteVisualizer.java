package view;


import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.Turtle;
import model.TurtleInterface;

/**
 * Visualizer component of the UI; displays the turtle image and lines drawn
 */
public class ConcreteVisualizer extends Element implements Visualizer, SizingElement {

	public static final Color DEFAULTPENCOLOR = Color.BLACK;
	public static final Color DEFAULTBORDERCOLOR = Color.BLACK;
	public static final Color DEFAULTBGCOLOR = Color.WHITE;
    
    public static final int TURTLEHEIGHT = 22;
    public static final int TURTLEWIDTH = 15;
    public static final int BORDER_SIZE = 5;
    public static final int BUFFER = 0;
    public static final int HALF = 2;
    public static final int ADJUSTEDANGLE = 270;
    public static final int INITSPEED = 500;
    public static final int SPEED = 1000;
		
	private Group group;
	private Group turtleAdjust;
	private double centerX;
	private double centerY;
	private Rectangle back;
	private Rectangle front;
	private FunctionDisplay fd;
	private VariableDisplay vd;
	private TurtleDisplay td;
	private ColorPalette cp;
	private ImagePalette ip;
	private List<TurtleInterface> turtles;
	private boolean animated;
	private int speed;
	private BooleanProperty paused;
	
	/**
	 * Constructor for ConcreteVisualizer
	 * @param fd
	 * @param vd
	 */
	public ConcreteVisualizer(FunctionDisplay fd, VariableDisplay vd, TurtleDisplay td, ColorPalette cp, ImagePalette ip) {
		super();
		animated = false;
		paused = new SimpleBooleanProperty(false);
		speed = INITSPEED;
		this.fd = fd;
		this.vd = vd;
		this.td = td;
		this.cp = cp;
		this.ip = ip;
		group = new Group();
		turtleAdjust = new Group();
		turtleAdjust.setRotate(ADJUSTEDANGLE);
		centerX = 0;
		centerY = 0;
		turtles = new ArrayList<>();
		back = new Rectangle(BUFFER, BUFFER, DEFAULTBORDERCOLOR);
		front = new Rectangle(BUFFER, BUFFER, DEFAULTBGCOLOR);
		addNodes();
	}

	/**
	 * add the items to be displayed to the group
	 */
	private void addNodes() {
		group.getChildren().add(back);
		group.getChildren().add(front);
		group.getChildren().add(turtleAdjust);
		addNode(group);
	}

	/**
	 * sets the background color; called in MenuBar class
	 * @param color
	 */
	protected void setBGColor(Color color) {
		front.setFill(color);
	}
	
	/**
	 * sets whether animation is running
	 * @param b
	 */
	public void setAnimate(boolean b) {
		animated = b;
	}
	
	/**
	 * returns whether animation is running
	 */
	public boolean getAnimate() {
		return animated;
	}
	
	/**
	 * sets the paused property
	 * @param b
	 */
	public void setPaused(boolean b) {
		paused.set(b);
	}
	
	/**
	 * gets the paused boolean
	 * @return
	 */
	public boolean getPaused() {
		return paused.get();
	}
	
	/**
	 * gets the paused property
	 */
	public BooleanProperty getPauseProperty() {
		return paused;
	}
	
	/**
	 * sets the width of the visualizer
	 */	
	public void setWidth(double w) {
		back.setLayoutX(BUFFER);
		front.setLayoutX(BORDER_SIZE + BUFFER);
		setPaneWidth(w);
		back.setWidth(w - HALF * BUFFER);
		front.setWidth(w - HALF * BORDER_SIZE - HALF * BUFFER);
		centerX = w / HALF;
		
	}
	
	/**
	 * sets the height of the visualizer
	 */	
	public void setHeight(double h) {
		setPaneHeight(h);
		back.setLayoutY(BUFFER);
		front.setLayoutY(BORDER_SIZE + BUFFER);
		back.setHeight(h - HALF * BUFFER);
		front.setHeight(h - HALF * BORDER_SIZE - HALF * BUFFER);
		centerY = h / HALF;
	}

	/**
	 * adjust the position of the offset group for turtles
	 * @param width
	 * @param height
	 */
	protected void adjustTurtle(double width, double height) {
		turtleAdjust.setLayoutX(width / HALF - turtles.get(0).getShape().getFitWidth() / HALF);
		turtleAdjust.setLayoutY(height / HALF - turtles.get(0).getShape().getFitHeight() / HALF);
	}
	
	/**
	 * sets the x position of the visualizer
	 */	
	@Override
	public void setElementX(double x) {
		this.setX(x);
	}
	
	/**
	 * sets the y position of the visualizer
	 */
	@Override
	public void setElementY(double y) {
		this.setY(y);
	}
	
	/**
	 * draws a line between the two points with given color and width
	 */
	@Override
	public void drawLine(double x1, double y1, double x2, double y2, Color penColor, double penWidth) {
		Line line = new Line();
		line.setStartX(centerX + x1); 
		line.setStartY(centerY - y1); 
		line.setEndX(centerX + x2); 
		line.setEndY(centerY - y2);
		line.setStroke(penColor);
		line.setStrokeWidth(penWidth);
		group.getChildren().add(line);
	}

	/**
	 * clears all the lines and resets the turtles to 0,0
	 */
	@Override
	public void reset() {
		for(int i = group.getChildren().size() - 1; i >= 0; i--) {
			if(group.getChildren().get(i) instanceof Line) {
				group.getChildren().remove(i);
			}
		}
		
		for(TurtleInterface ti: turtles) {
			double temp = ti.getPenWidthProperty().get();
			ti.setPenWidth(0);
			ti.moveTo(0, 0);
			ti.setPenWidth(temp);
		}
	}
	
	/**
	 * adds the variable to variable display
	 */
	@Override
	public void addVariable(String var, double val) {
		vd.addVariable(var, val);
	}

	/**
	 * removes the variable from variable display
	 */
	@Override
	public void removeVariable(String var, double val) {
		vd.removeVariable(var, val);
	}

	/**
	 * adds the function to function display
	 */
	@Override
	public void addFunction(String name) {
		fd.addFunction(name);
	}

	/**
	 * removes the function from function display
	 */
	@Override
	public void removeFunction(String name) {
		fd.removeFunction(name);
	}
	
	/**
	 * adds a turtle to be displayed
	 */
	@Override
	public void addTurtle(TurtleInterface t) {
		t.getShape().setFitHeight(TURTLEHEIGHT);
		t.getShape().setFitWidth(TURTLEWIDTH);
		t.getShape().setX(0);
		t.getShape().setY(0);
		t.getShape().setOnMouseClicked(e -> {
			t.toggleActive();
		});
		turtleAdjust.getChildren().add(t.getShape());
		td.addTurtle(t);
		turtles.add(t);
	}

	/**
	 * sets the background color to be the color at index position
	 */
	@Override
	public void setBGColor(double index) {
		setBGColor(getColorIndex((int) index));
	}

	/**
	 * sets the color at the index position
	 */
	@Override
	public void setPalette(double index, Color color) {
		cp.getData().set((int) index, color.toString());
	}

	/**
	 * returns the color at the index position
	 */
	@Override
	public Color getColorIndex(int index) {
		return Color.web(cp.getData().get(index));
	}

	/**
	 * returns the shape at the index position
	 */
	@Override
	public Image getShapeIndex(int index) {
		return new Image(Turtle.class.getClassLoader().getResourceAsStream(ip.getData().get(index)));
	}

	/**
	 * returns the height of the background
	 */
	@Override
	public double getBGHeight() {
		return front.getHeight();
	}

	/**
	 * returns the width of the background
	 */
	@Override
	public double getBGWidth() {
		return front.getWidth();
	}
	
	/**
	 * sets the animation speed
	 * @param millis
	 */
	public void setSpeed(int millis) {
		speed = millis;
	}
	
	/**
	 * returns the animation speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * allows the animation speed to change
	 * @param dp
	 */
	public void setSliderListener(DoubleProperty dp) {
		dp.addListener((newv, oldv, o) -> {
			speed = new Double(newv.getValue().doubleValue() * SPEED).intValue(); 
			if(speed == 0) {
				speed = 1;
			}
		});
	}

}
