package basic.example;
// ui: Root.fxml(기본), AddForm.fxml(추가), BarChart.fxml(차트)
// controller : RootController.java

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Root.fxml"));
		BorderPane root = loader.load();
		
		// 루트에 있는 컨트롤 가져옴 (정적메소드에서 가져올 수 없음) 컨트롤 타입을 리턴
		RootController controller =  loader.getController(); 
		// RootController에 있는 메소드나 필드 호출 가능
		controller.setPrimaryStage(primaryStage);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false); // 화면 크기조절 불가능
		primaryStage.setTitle("화면");
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
