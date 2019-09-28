package view;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.CodeInterpreter;
import model.SLogoException;

/**
 * External interface for function display, so that Model can update active user-made commands.
 */
public abstract class Display extends Element implements SizingElement {
	
	public static final int BUFFER = 2;
	public static final String NAMEALERT = "Please do not change variable names.";
	public static final String VALUEALERT = "Please use valid variable values.";
	public static final String NAMEALERTHEAD = "Variable Change Error";
		
	//private ListView<String> lv;
	private ScrollPane sp;
	private VBox vb;
	private CodeInterpreter ci;
	private BooleanProperty active = new SimpleBooleanProperty();
	
	private Label name;
	
	public Display(String n) {
		this.ci = null;
		name = new Label(n);
		vb = new VBox();
		sp = new ScrollPane(vb);
		sp.setLayoutX(BUFFER);
		sp.setLayoutY(BUFFER);
		name.setLayoutX(BUFFER);
		name.setLayoutY(BUFFER);
		addNode(sp);
		addNode(name);
		
		active.set(true);
		
	}
	
	public Display() {
		this("Display");
	}
	
	public BooleanProperty getActive() {
		return active;
	}
	
	/**
	 * Adds a function to the display. Display automatically updates.
	 * @param func	Function name as a String
	 */
	public void addItem(String func, Color c) {
		//lv.getItems().add(func);
		VarTextField tf = new VarTextField(func);
		tf.setStyle("-fx-control-inner-background: #"+c.toString().substring(4));
		tf.getStyleClass().add("text-field");
		vb.getChildren().add(tf);
		tf.setEditable(false);
		//tf.setPrefWidth(sp.getPrefWidth());
		
		tf.setOnAction(e -> {
			if(tf.isEditable() && ci != null) {
				if(!tf.compareName()) {
					new MakeAlert(NAMEALERTHEAD, NAMEALERT);
					tf.revert();
				}
				try {
					ci.execute(new String[] {tf.getCommand()});
					tf.updateValue();
				} catch (SLogoException se) {
					new MakeAlert(NAMEALERTHEAD, VALUEALERT);
					tf.revert();
				}
			}
		});
	}
	
	public void addItem(String func) {
		addItem(func, Color.WHITE);
	}
	
	/**
	 * If the function is being displayed, remove it and stop displaying it.
	 * @param func	Function name to be removed
	 */
	public void removeItem(String func) {
		//if(lv.getItems().contains(func)) {
		//	lv.getItems().remove(func);
		//}
		for(int k = vb.getChildren().size() - 1; k >= 0; k--) {
			Node a = vb.getChildren().get(k);
			if(a instanceof VarTextField) {
				VarTextField tf = (VarTextField) a;
				String funcName = func.split(VarTextField.CHANGE_VAR_COMMAND[1])[0];
				if(tf.getName().equals(funcName)) {
					vb.getChildren().remove(a);
				}
			}
		}
	}
	
	public void setWidth(double w) {
		sp.setPrefWidth(w-BUFFER*2);
		name.setPrefWidth(w-BUFFER*2);
		setPaneWidth(w);
		name.setAlignment(Pos.CENTER);
		vb.setPrefWidth(sp.getPrefWidth());
	}
	
	public void setHeight(double h) {
		sp.setPrefHeight(h*9/10-BUFFER);
		name.setPrefHeight(h/10-BUFFER);
		sp.setLayoutY(h/10 + BUFFER);
		setPaneHeight(h);
		vb.setPrefHeight(sp.getPrefHeight());
	}
	
	public void makeEditable(boolean b) {
		vb.getChildren().forEach(item -> {
			if(item instanceof TextField) {
				((TextField) item).setEditable(b);
			}
		});
	}
	
	public void setElementX(double x) {
		this.setX(x);
	}
	
	public void setElementY(double y) {
		this.setY(y);
	}
	
	public void setCI(CodeInterpreter ci) {
		this.ci = ci;
	}

	public void hide() {
		getPane().toBack();
	}
	
	public void show() {
		getPane().toFront();
	}
	
	public String getName() {
		return name.getText();
	}
	
}
