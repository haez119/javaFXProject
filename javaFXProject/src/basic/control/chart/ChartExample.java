package basic.control.chart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

// ui : Chart.fxml
// control: ChartController.java

public class ChartExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		HBox h = FXMLLoader.load(getClass().getResource("Chart.fxml"));
		
		Scene scene = new Scene(h);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
