package basic.container;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// fxml의 root 파일을 자바코드로 이해하기
public class VBoxExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox root = new VBox();
		root.setPadding(new Insets(10, 10, 10, 10));

		// 컨트롤 먼저 만들기
		ImageView iv = new ImageView();
		iv.setFitWidth(200);
		iv.setPreserveRatio(true);
		iv.setImage(new Image("/basic_images/fruit1.jpg"));

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);

		Button btnPrev = new Button();
		btnPrev.setText("이전");
		Button btnNext = new Button("다음");
		HBox.setHgrow(btnNext, Priority.ALWAYS);
		btnNext.setMaxWidth(Double.MAX_VALUE);
		hbox.getChildren().add(btnPrev);
		hbox.getChildren().add(btnNext); // </children>

		VBox.setMargin(hbox, new Insets(10)); // vbox의 margin을 설정 ( 대상?, 값)

		// 이벤트 핸들러를 해당 컨트롤에 등록하기
		// 다음버튼 누르면 fruit1 -> 2->3 ... 바뀌게 하기
		
	
		btnNext.setOnAction(new EventHandler<ActionEvent>() {
			int loc = 2;

			@Override
			public void handle(ActionEvent ac) {
				if (loc == 9)
					loc = 1;
				iv.setImage(new Image("/basic_images/fruit" + loc++ + ".jpg"));

			}
		});

		// 다시하기
//		btnPrev.setOnAction(new EventHandler<ActionEvent>() {
//			int loc1 = 1;
//			
//			@Override
//			public void handle(ActionEvent ac) {
//
//				if (loc == 0)
//					loc= loc1 ;
//				iv.setImage(new Image("/basic_images/fruit" + loc-- + ".jpg"));
//
//			}
//		});

		root.getChildren().add(iv); // children 테그 밑에 들어가는 컨트롤들 넣기
		root.getChildren().add(hbox);

		// 화면으로 보여줄 때 규칙 스테이지(씬(컨테이너(컨트롤)))

		// 컨테이너 씬에 담기
		Scene scene = new Scene(root);

		// 씬을 윈도우 역할 하는 스테이지에 담기
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("VBox 예제");

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
