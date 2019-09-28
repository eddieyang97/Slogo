package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * A super class that allows for creating a palette ui element; displays a listview of information
 */
public class Palette extends Element implements SizingElement {

	public static final double LABELHEIGHT = 15;

	private ListView<String> lv = new ListView<>();
	private ObservableList<String> data = FXCollections.observableArrayList();
	private final Label label = new Label();
	
	public Palette() {
		super();
		lv.setItems(data);
		lv.setLayoutY(LABELHEIGHT);
		addNode(lv);
		addNode(label);
	}

	protected void setText(String s) {
		label.setText(s);
	}

	protected ListView<String> getLV() {
		return lv;
	}
    
    /**
     * return the colors stored in the data list
     * @return
     */
    protected ObservableList<String> getData() {
    	return data;
	}
    
	@Override
	public void setElementX(double x) {
		this.setX(x);
	}

	@Override
	public void setElementY(double y) {
		this.setY(y);
	}

	@Override
	public void setHeight(double h) {
		setPaneHeight(h);
		lv.setPrefHeight(h);
	}

	@Override
	public void setWidth(double w) {
		label.setPrefWidth(w);
		label.setAlignment(Pos.CENTER);
		setPaneWidth(w);
		lv.setPrefWidth(w);
	}
}
