package view;

import javafx.scene.control.TextField;

public class VarTextField extends TextField {
	
	protected static final String[] CHANGE_VAR_COMMAND = {"make ", "= "};
	
	private String name;
	private String value;
	
	public VarTextField(String s) {
		super(s);
		name = s.split(CHANGE_VAR_COMMAND[1])[0];
		updateValue();
	}
	
	public boolean compareName() {
		return name.equals(getText().split(CHANGE_VAR_COMMAND[1])[0]);
	}
	
	public String getCommand() {
		return CHANGE_VAR_COMMAND[0] + getText().split(CHANGE_VAR_COMMAND[1])[0] + getText().split(CHANGE_VAR_COMMAND[1])[1];
	}
	
	public void revert() {
		setText(name + CHANGE_VAR_COMMAND[1] + value);
	}
	
	public String getName() {
		return name;
	}
	
	public void updateValue() {
		if(getText().split(CHANGE_VAR_COMMAND[1]).length > 1) {
			value = getText().split(CHANGE_VAR_COMMAND[1])[1];
		}
	}
}
