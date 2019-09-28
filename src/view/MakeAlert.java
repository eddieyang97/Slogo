package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MakeAlert {

	public MakeAlert(String head, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(head);
		alert.setContentText(content);
		alert.show();
	}
	
}
