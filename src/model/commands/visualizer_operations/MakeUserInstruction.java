package model.commands.visualizer_operations;
import model.SLogoException;
import model.commands.Function;
import model.commands.ListCommand;
import view.Visualizer;
/**
 * Makes a user command and displays it 
 * @author andrew
 *
 */
public class MakeUserInstruction extends VisualizerCommand {
	public MakeUserInstruction(Visualizer vis) {
		super(vis, 1);
	}
	@Override
	public double run() {
		exceptionCheck();
		double answer = 0;
		for(int x = 0; x < getChildren().size(); x += 2) {
			answer = act(x);
		}
		return answer;
	}
	@Override
	public void exceptionCheck() {
		if(getChildren().size() % 2 != 0 || getChildren().size() == 0)
			throw new SLogoException("Invalid number of arguments from grouping");
	}
	@Override
	public double act(int index) {
		if(!(getChild(index) instanceof Function) || !(getChild(index + 1) instanceof ListCommand)) {
			return 0;
		}
		Function f = (Function)(getChildren().get(index));
		((ListCommand) (f.getChild(1))).overwrite(getChildren().get(index+1));
		if(!f.correctCheck()) {
			return 0;
		}
		myVisualizer.removeFunction(f.getName());
		myVisualizer.addFunction(f.getName());
		return 1;
	}

}
