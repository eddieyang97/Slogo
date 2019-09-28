package view;

public class FunctionDisplay extends Display {

	public static final String TITLE = "Functions";
	
	public FunctionDisplay() {
		super(TITLE);
		
	}
	
	public void addFunction(String name) {
		this.addItem(name);
	}
	
	public void removeFunction(String name) {
		this.removeItem(name);
	}
	
	
}
