package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.CodeInterpreter;
import model.SLogoCodeInterpreter;

/**
 * Overarching GUI class that instantiates other GUI elements and places them appropriately. Also controls language properties.
 */
public class Gui {
	
	public static final int MENU_BUFFER = 30;
	public static final int BUFFER = 5;
	
	public static final double VISUALIZER_WIDTH_RATIO = 3.0 / 4;
	public static final double VISUALIZER_HEIGHT_RATIO = 2.0 / 3;
	
	public static final double CONSOLE_WIDTH_RATIO = VISUALIZER_WIDTH_RATIO;
	public static final double CONSOLE_HEIGHT_RATIO = 2.0 / 9;
	
	public static final double TEXT_WIDTH_RATIO = VISUALIZER_WIDTH_RATIO;
	public static final double TEXT_HEIGHT_RATIO = 1 - VISUALIZER_HEIGHT_RATIO - CONSOLE_HEIGHT_RATIO;
	
	public static final double VARIABLE_WIDTH_RATIO = 1 - VISUALIZER_WIDTH_RATIO;
	public static final double VARIABLE_HEIGHT_RATIO = 1.0 / 4;
	
	public static final double FUNCTION_WIDTH_RATIO = 1 - VISUALIZER_WIDTH_RATIO;
	public static final double FUNCTION_HEIGHT_RATIO = 1.0 / 4;
	
	public static final double TURTLE_WIDTH_RATIO = 1 - VISUALIZER_WIDTH_RATIO;
	public static final double TURTLE_HEIGHT_RATIO = 1.0 / 4;
	
	public static final double COLORPALETTE_Y_RATIO = 0.61;
	public static final double IMAGEPALETTE_Y_RATIO = 0.8;
	public static final double PALETTE_HEIGHT_RATIO = 0.16;
	
	private double aWidth = VISUALIZER_WIDTH_RATIO;
	private double bWidth = 1 - VISUALIZER_WIDTH_RATIO;
	
	private double activeDisplays = 3;
	private double dispHeight = 0.6 / activeDisplays;
	private List<Display> displays = new ArrayList<>();
	
	public static final String STYLE_SHEET = "data/style/gui.css";
	
	
	private ConcreteVisualizer vi;
	private Console co;
	private TextBox tb;
	private MenuBar mb;
	private ColorPalette cp;
	private ImagePalette ip;
	private Rectangle hideTop;
	private Rectangle hideLeft;
	private Rectangle hideRight;
	private Rectangle hideBot;
	private double currentW;
	private double currentH;
	
	private Group root;
	
	public Gui() {
		root = new Group();
	}
	
	
	public Scene setScene(Stage stage, double initx, double inity) {
		root = new Group();
        Scene scene = new Scene(root, initx, inity);
        FunctionDisplay fd = new FunctionDisplay();
        VariableDisplay vd = new VariableDisplay();
        TurtleDisplay td = new TurtleDisplay();
        PaletteDisplay pd = new PaletteDisplay();
        
        displays.add(fd);
        displays.add(vd);
        displays.add(td);
        
        setUpDisplays(displays);
        
        
        pd.addColor(0, Color.BLUE);
        
        cp = new ColorPalette();
        ip = new ImagePalette();
        vi = new ConcreteVisualizer(fd, vd, td, cp, ip);
        CodeInterpreter ci = new SLogoCodeInterpreter(vi);
        co = new Console(ci);
        tb = new TextBox(stage, ci, co);
        mb = new MenuBar(stage, vi, tb, ci, displays.toArray(new Display[displays.size()]));
        
        fd.setCI(ci);
        vd.setCI(ci);
        td.setCI(ci);
        
        hideTop = new Rectangle(0, 0, ConcreteVisualizer.DEFAULTBGCOLOR);
        hideTop.getStyleClass().add("rectangle");
        hideBot = new Rectangle(0, 0, ConcreteVisualizer.DEFAULTBGCOLOR);
        hideLeft = new Rectangle(0, 0, ConcreteVisualizer.DEFAULTBGCOLOR);
        hideRight = new Rectangle(0, 0, ConcreteVisualizer.DEFAULTBGCOLOR);
        
        root.getChildren().addAll(hideTop, hideLeft, hideRight, hideBot);
        addElements(fd, vd, mb, co, tb, vi, td, cp, ip);

        vi.getPane().toBack();
        currentW = initx;
        currentH = inity;
        resize(initx, inity);
        
        vi.reset();
        
        File style = new File(STYLE_SHEET);
        
        scene.getStylesheets().add(style.toURI().toString());
       
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldWidth, Number newWidth) {
                currentW = newWidth.doubleValue();
                resize(currentW, currentH);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldHeight, Number newHeight) {
            	currentH = newHeight.doubleValue();
                resize(currentW, currentH);
            }
        });
        

        return scene;
	}
	
	
	private void setUpDisplays(List<Display> displays2) {
		displays.forEach(item -> item.getActive().addListener((o, oldv, newv) -> {
        	if(!newv.booleanValue()) {
        		displays.remove(item);
        		item.hide();
        		if(displays.size() == 0) {
        			bWidth = 0;
        			aWidth = 1.0;
        		} else {
        			dispHeight = 0.6 / displays.size();
        		}
        	} else {
        		displays.add(item);
        		item.show();
        		bWidth = 1 - VISUALIZER_WIDTH_RATIO;
        		aWidth = VISUALIZER_WIDTH_RATIO;
        		dispHeight = 0.6 / displays.size();
        	}
        	
        	resize(currentW, currentH);
        }));
		
	}


	private void addElements(Element...elements) {
		 for(Element e : elements) {
			 root.getChildren().add(e.getPane());
		 }
	}
	
	
	private void resize(double w, double h) {
		double openH = h - MENU_BUFFER - BUFFER * 2;
		double openW = w - BUFFER * 2;
		
		resizeElement(vi, openW * aWidth, openH * VISUALIZER_HEIGHT_RATIO, BUFFER, MENU_BUFFER + BUFFER);
		vi.adjustTurtle(openW * aWidth, openH * VISUALIZER_HEIGHT_RATIO);
		
		resizeElement(tb, openW * aWidth, openH * TEXT_HEIGHT_RATIO, BUFFER, MENU_BUFFER + BUFFER + openH * (1 - TEXT_HEIGHT_RATIO));
		
		resizeElement(co, openW * aWidth, openH * CONSOLE_HEIGHT_RATIO, BUFFER, MENU_BUFFER + BUFFER + openH * VISUALIZER_HEIGHT_RATIO);
		
		resizeDisplays(openW, openH);

		resizeElement(cp, openW * bWidth - BUFFER, h * PALETTE_HEIGHT_RATIO, w * aWidth, BUFFER + COLORPALETTE_Y_RATIO * h);
		
		resizeElement(ip, openW * bWidth - BUFFER, h * PALETTE_HEIGHT_RATIO, w * aWidth, BUFFER + IMAGEPALETTE_Y_RATIO * h);
        
        hideLeft.setX(0);
        hideLeft.setY(0);
        hideLeft.setHeight(h);
        hideLeft.setWidth(BUFFER);
        
        hideTop.setX(0);
        hideTop.setY(0);
        hideTop.setHeight(MENU_BUFFER + BUFFER);
        hideTop.setWidth(w);
        
        hideBot.setX(0);
        hideBot.setY(MENU_BUFFER + BUFFER + openH * VISUALIZER_HEIGHT_RATIO);
        hideBot.setHeight(h - (MENU_BUFFER + BUFFER + openH * VISUALIZER_HEIGHT_RATIO));
        hideBot.setWidth(w);
        
        hideRight.setX(BUFFER + openW * aWidth);
        hideRight.setY(0);
        hideRight.setWidth(w - (BUFFER + openW * aWidth));
        hideRight.setHeight(h);

        mb.setX(BUFFER);
        mb.setY(0);
        mb.setPaneWidth(openW);
	}
	
	private void resizeDisplays(double w, double h) {
		
		for(int k = 0; k < displays.size(); k++) {
			resizeElement(displays.get(k), w * bWidth, h * dispHeight, BUFFER + w * aWidth, MENU_BUFFER + BUFFER + k * dispHeight * h);
		}
	}
	
	private void resizeElement(SizingElement e, double w, double h, double x, double y) {
		e.setWidth(w);
        e.setHeight(h);
        e.setElementX(x);
        e.setElementY(y);
	}
	
}
