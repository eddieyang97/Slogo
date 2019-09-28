package view;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Element {
	
	private Pane pane;
	
	public Element(Pane p) {
		pane = p;
	}
	
	public Element() {
		this(new Pane());
	}
	
	public void setX(double x) {
		pane.setLayoutX(x);
	}
	
	public void setY(double y) {
		pane.setLayoutY(y);
	}
	
	public void setPaneWidth(double w) {
		pane.setPrefWidth(w);
	}
	
	public void setPaneHeight(double h) {
		pane.setPrefHeight(h);
	}
	
	
	public Pane getPane() {
		return pane;
	}
	
	public void addNode(Node n) {
		pane.getChildren().add(n);
	}
	
}
