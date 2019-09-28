package view;

import javafx.scene.paint.Color;

public class PaletteDisplay extends Display {

	public static final String TITLE = "Palettes";
	
	public PaletteDisplay() {
		super(TITLE);
		
	}
	
	public void addColor(double val, Color c) {
		this.addItem(Double.toString(val), c);
		makeEditable(true);
	}
	
	public void removeColor(double val) {
		this.removeItem(Double.toString(val));
	}
	
}
