package model.commands.visualizer_operations;

import javafx.scene.paint.Color;
import model.SLogoException;
import view.Visualizer;

/**
 * changes one index of the palette
 * @author jeffreyli
 *
 */
public class SetPalette extends VisualizerCommand {

	public SetPalette(Visualizer vis) {
		super(vis, 4);
	}

	@Override
	public double act(int index) {
		// TODO Auto-generated method stub
		double value = childValue(0);
		Color color;
		try {
			color = Color.rgb((int) childValue(index+1), (int) childValue(index + 2), (int) childValue(index + 3));
		}
		catch (IllegalArgumentException e){
			throw new SLogoException("Invalid color defined!");
		}
		myVisualizer.setPalette(value, color);
		return value;
	}

}
