package basic.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//ui : BoardControl.fxml
// control : BoardCotroller.java
// Board.java

public class BoardExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		AnchorPane a = FXMLLoader.load(getClass().getResource("BoardControl.fxml"));

		Scene scene = new Scene(a);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
