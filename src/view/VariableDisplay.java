package view;

/**
 * External interface for variable display, so that Model can update active variables.
 */
public class VariableDisplay extends Display{
	
	public static final String TITLE = "Variables";
	
	public VariableDisplay() {
		super(TITLE);
	}
	
	public void addVariable(String var, double val) {
		this.addItem(var + " = " + val);
		makeEditable(true);
	}
	
	public void removeVariable(String var, double val) {
		this.removeItem(var + " = " + val);
	}
	
}
