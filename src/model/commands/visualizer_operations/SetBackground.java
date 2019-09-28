package model.commands.visualizer_operations;

import view.Visualizer;
/**
 * 
 * @author jeffreyli
 * Changes the background colour to the colour in the palette at index 
 */
public class SetBackground extends VisualizerCommand{

	public SetBackground(Visualizer vis) {
		super(vis, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double act(int index) {
		double value = childValue(index);
		myVisualizer.setBGColor(value);
		return value;
	}

}
