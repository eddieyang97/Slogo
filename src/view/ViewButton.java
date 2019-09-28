package view;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ViewButton {
	private Button button;
	private String id;
	private String text;
	
	public ViewButton(String id) {
		button = new Button();
		this.id = id;
		this.text = id;
		
		ResourceBundle rb = null;
		try {
			rb = ResourceBundle.getBundle(MenuBar.DEFAULT_LANG);
		} catch (Exception e) {
			button.setText(text);
		}
		
		setLang(rb);
	}
	
	public ViewButton() {
		this("Default");
	}
	
	public void addHandler(EventHandler<ActionEvent> eh) {
		button.setOnAction(eh);
	}
	
	public Button getButton() {
		return button;
	}
	
	public void setLang(ResourceBundle newRB) {
		try {
			text = newRB.getString(id);
		} catch (Exception e) {
			// Do nothing, goes to default
		}
		button.setText(text);
	}

	public void setAdditionalText(String s) {
		button.setText(text+ ": " + s);
	}
	
	public void setText(String s) {
		button.setText(s);
		text = s;
	}
	
}
