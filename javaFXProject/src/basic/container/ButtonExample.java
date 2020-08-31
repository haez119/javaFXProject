package basic.container;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

// UI 담당 : Button.fxml
// Controller : ButtonController.java

public class ButtonExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane button = FXMLLoader.load(getClass().getResource("Button.fxml"));
		
		Scene scene = new Scene(button);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
