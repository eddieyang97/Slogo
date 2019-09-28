package view;

public class ImagePalette extends Palette {
	
	public static final String IMAGELABEL = "ImagePalette";
	public static final String[] INITPICS = {"turtle.png", "turt.gif", "turtle2.png"};
	
	/**
	 * Constructor for imagepalette
	 */
	public ImagePalette() {
		super();
		setText(IMAGELABEL);
		getData().addAll(INITPICS);
	}
}
