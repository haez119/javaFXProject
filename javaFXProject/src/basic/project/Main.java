package basic.project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {

		AnchorPane root = FXMLLoader.load(getClass().getResource("BookAddList.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		primaryStage.setTitle("도서등록");
	}
	public static void main(String[] args) {
		launch(args);
	}
	

}
