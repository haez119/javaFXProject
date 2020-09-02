package basic.control;
//ui : View.fxml
//control: ViewController.java

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane a = FXMLLoader.load(getClass().getResource("View.fxml"));
		
		Scene scene = new Scene(a);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
