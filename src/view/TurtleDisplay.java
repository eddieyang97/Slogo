package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.TurtleInterface;

public class TurtleDisplay extends Display {
	
	public static final String TITLE = "Turtles";
	public static final String BASE = "Turtle ";
	public static final String LINK1 = " |Pen ";
	public static final String PEN_DOWN = "|DOWN|";
	public static final String PEN_UP   = "|UP  |";
	private static final String[] PEN_STATES = {PEN_DOWN, PEN_UP};
	public static final String LINK2 = "%3d°|";
	public static final String LINK3 = "%2d px|";
	public static final String ACTIVE =   "ACTIVE  |";
	public static final String INACTIVE = "INACTIVE|";
	private static final String[] ACTIVE_STATES = {ACTIVE, INACTIVE};
	public static final String LINK4 = "x: %4d|";
	public static final String LINK5 = "y: %4d|";
	
	private List<TurtleInterface> turtles;
	private Map<TurtleInterface, StringBuilder> displayedStrings;
	
	public TurtleDisplay() {
		super(TITLE);
		turtles = new ArrayList<>();
		displayedStrings = new HashMap<>();
	}
	
	public void addTurtle(TurtleInterface ti) {
		if(!turtles.contains(ti)) {
			turtles.add(ti);
			String initPen = (ti.getPenDownProperty().get()) ? PEN_DOWN : PEN_UP;
			String initAng = String.format(LINK2, ti.getRotationProperty().getValue().intValue());
			String initWid = String.format(LINK3, ti.getPenWidthProperty().getValue().intValue());
			String initAct = (ti.getActiveProperty().get()) ? ACTIVE : INACTIVE;
			String initX = String.format(LINK4, ti.getShapeXProperty().getValue().intValue());
			String initY = String.format(LINK5, ti.getShapeYProperty().getValue().intValue());
			
			StringBuilder display = new StringBuilder(BASE+(int) ti.getID() + initPen + initAng + initWid + initAct + initX + initY);
			
			int[] penIndex = new int[3];
			int[] angleIndex = new int[3];
			int[] widthIndex = new int[3];
			int[] activeIndex = new int[3];
			int[] xIndex = new int[3];
			int[] yIndex = new int[3];
			
			penIndex[2] = initPen.length();
			angleIndex[2] = initAng.length();
			widthIndex[2] = initWid.length();
			activeIndex[2] = initAct.length();
			xIndex[2] = initX.length();
			yIndex[2] = initY.length();
			
			setIndexes((BASE + (int) ti.getID()).length(), penIndex, angleIndex, widthIndex, activeIndex, xIndex, yIndex);
			
			displayedStrings.put(ti, display);
			addItem(display.toString());
			setListener(ti, penIndex, angleIndex, widthIndex, activeIndex, xIndex, yIndex);
		}
	}
	
	public void setListener(TurtleInterface ti, int[] ... indexes) {
		StringBuilder sb = displayedStrings.get(ti);
		ti.getPenDownProperty().addListener((o, oldv, newv) -> boolListener(newv, sb, PEN_STATES, indexes[0]));
		ti.getTrueRotationProperty().addListener((o, oldv, newv) -> numListener(new Double((360-newv.doubleValue())%360), sb, LINK2, indexes[1]));
		ti.getPenWidthProperty().addListener((o, oldv, newv) -> numListener(newv, sb, LINK3, indexes[2]));
		ti.getActiveProperty().addListener((o, oldv, newv) -> boolListener(newv, sb, ACTIVE_STATES, indexes[3])); 
		ti.getShapeXProperty().addListener((o, oldv, newv) -> numListener(newv, sb, LINK4, indexes[4]));
		ti.getShapeYProperty().addListener((o, oldv, newv) -> numListener(new Double(-newv.doubleValue()), sb, LINK5, indexes[5]));
	}
	
	private void boolListener(Boolean newv, StringBuilder sb, String[] choices, int[] index) {
		removeItem(sb.toString());
		String append = (newv) ? choices[0] : choices[1];
		sb.replace(index[0], index[1], append);	
		addItem(sb.toString());
	}
	
	private void numListener(Number newv, StringBuilder sb, String link, int[] index) {
		removeItem(sb.toString());
		String append = String.format(link, newv.intValue());
		sb.replace(index[0], index[1], append);
		addItem(sb.toString());
	}

	private void setIndexes(int base, int[]...arrays) {
		
		for(int k = 0; k < arrays.length; k++) {
			arrays[k][0] = (k == 0) ? base : arrays[k-1][1];
			arrays[k][1] = arrays[k][0] + arrays[k][2];
		}
	}
	
}
	

