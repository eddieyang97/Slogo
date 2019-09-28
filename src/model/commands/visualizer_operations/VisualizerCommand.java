package model.commands.visualizer_operations;
/**
 * Abstract class for classes that need the Visualizer
 * @author andrew
 */
import model.commands.Command;
import view.Visualizer;

public abstract class VisualizerCommand extends Command{
	protected Visualizer myVisualizer;
	public VisualizerCommand(Visualizer vis, int numVar) {
		super(numVar);
		myVisualizer = vis;
	}
}
