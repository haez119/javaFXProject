package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HBox hbox = new HBox(); // 컨테이너
		
		
		hbox.setPadding(new Insets(10)); //  컨테이너와 컨트롤의 여백
		hbox.setSpacing(10); // 컨트롤 끼리의 간격
		
		// 컨트롤
		// 텍스트박스
		TextField tField = new TextField(); 
		tField.setPrefWidth(200);
		// 버튼
		Button btn = new Button();
		btn.setText("확인");
		
		// 버튼에 이벤트 넣기 ( 누르면 닫히는 )
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}
		});
		
		// 컨테이너에 컨트롤 달기
		hbox.getChildren().add(tField);
		hbox.getChildren().add(btn);
		
		// 씬 생성 (씬은 생성할 때 컨테이너를 넣어줘야 함
		Scene scene = new Scene(hbox);
		
		// 씬 실행, 씬 보여주기
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("App 메인");
		
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
		
	}
}
