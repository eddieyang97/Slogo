package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import model.CodeInterpreter;

/**
 * Console component of the UI; displays history of code that has been run and
 * results of calculations
 */
public class Console extends Element implements SizingElement {

	public static final String NEWLINE = "\n";

	public static final int BUFFER = 2;
	
	private final ListView<String> lv = new ListView<>();
	
	/**
	 * Constructor for the console
	 * @param ci
	 */
	public Console(CodeInterpreter ci) {
	    super();
		lv.setLayoutX(BUFFER);
		lv.setLayoutY(BUFFER);
	    lv.setOnMouseClicked(e -> {
	    	if(lv.getSelectionModel().getSelectedItem() != null) {
	    		ci.execute(lv.getSelectionModel().getSelectedItem().split(NEWLINE));
	    	}
	    });
	    ObservableList<String> items = FXCollections.observableArrayList();
	    lv.setItems(items);
	    addNode(lv);
	}
	
	/**
	 * clear all the text displayed in console
	 */
	protected void clear() {
		lv.getItems().clear();
	}
	
	/**
	 * displays the text s in the console
	 * @param s
	 */
	protected void displayText(String s) {
		lv.getItems().add(s);
	}

	/**
	 * sets the width of the console
	 */
	public void setWidth(double w) {
		setPaneWidth(w);
		lv.setPrefWidth(w - BUFFER * 2);
	}
	
	/**
	 * sets the height of the console
	 */
	public void setHeight(double h) {
		setPaneHeight(h);
		lv.setPrefHeight(h - BUFFER * 2);
	}
	
	/**
	 * sets the x position of the console
	 */
	public void setElementX(double x) {
		this.setX(x);
	}

	/**
	 * sets the y position of the console
	 */
	public void setElementY(double y) {
		this.setY(y);
	}
	
}
