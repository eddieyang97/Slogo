package view;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

/**
 * Sub-class of Palette, displays an edible listview of color for user to choose from to set background color and pen color
 */
public class ColorPalette extends Palette{
	
	public static final String COLORLABEL = "ColorPalette";
	public static final double RECWIDTH = 190;
	public static final double RECHEIGHT = 20;
	public static final String[] INITCOLORS = {"red", "green", "blue", "yellow", "black", "white"};
	
	/**
	 * Constructor for colorpalette
	 */
	public ColorPalette() {
		super();
		setText(COLORLABEL);
		getData().addAll(INITCOLORS);
		getLV().setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> list) {
				return new ColorRectCell();
			}
		});
	}
	
    private static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(RECWIDTH, RECHEIGHT);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }

}
