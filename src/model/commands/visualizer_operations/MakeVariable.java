package model.commands.visualizer_operations;
/**
 * The MakeVariable command, which sets a variable equal to a value and updates the display
 * @author andrew
 */
import model.SLogoException;
import model.commands.Variable;
import view.Visualizer;


public class MakeVariable extends VisualizerCommand {
	public MakeVariable(Visualizer vis) {
		super(vis, 2);
	}

	@Override
	public double act(int index) {
		Variable var;
		try {
			
			var = (Variable) getChild(index);
			
		} catch (Exception e) {
			throw new SLogoException("Incorrect syntax for setting variables.");
		}
		try {
			myVisualizer.removeVariable(var.getName(), var.run());
		}
		catch(SLogoException e) { var.setValue(0);}
		double d = childValue(index+1);
		var.setValue(d);
		myVisualizer.addVariable(var.getName(), var.run());
		return d;
	}

}
