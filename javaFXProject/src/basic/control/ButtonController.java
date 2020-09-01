package basic.control;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;



public class ButtonController implements Initializable{
	
	
	@FXML private ToggleGroup group;
	@FXML private ImageView radioImageView, checkImageView;
	@FXML private RadioButton rad1, rad2, rad3;
	@FXML private CheckBox chk1, chk2;
	@FXML private Button btnExit;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// 그룹의 속성 값이 바뀔 때 마다 이벤트 등록 >> 리스너 사용?
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			
			
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				// System.out.println(observable.getValue().toString());  radiobutton의 속성값? id, styleClass, 선택된 차트id?
				if(oldValue != null && newValue != null) {
					System.out.println(oldValue.getUserData());
					//newValue.getUserData().toString() >> 새로 선택된 값의 이름을 가져옴
					radioImageView.setImage(new Image("basic_images/" + newValue.getUserData().toString() + ".png")); 
				}
			}
		});// 토클의 속성값을 가져옴?? 토글 값이 계속 바뀌니까 changeListener로
		
		//rad1의 마우스가 클릭 되는 이벤트가 발생하면
		rad1.setOnMouseClicked(new EventHandler<MouseEvent> () {

			@Override
			public void handle(MouseEvent me) {
				System.out.println("rad1 clicked.");
			}
		});
		rad2.setOnMouseClicked((a) -> System.out.println("rad2 clicked."));
		rad3.setSelected(true); // 실행할 때 기본으로 선택되게 하는거/ 처음 실행하면 3번이 기본선택 되어 있음
		
// 		이미지 바꿔주는 메소드인 handlerChkAction 메소드를 fxml파일인 컨트롤에 넣어줘도 됨
//		chk1.setOnAction(new EventHandler<ActionEvent> () {
//			@Override
//			public void handle(ActionEvent event) {
//				handlerChkAction();
//			}
//		});
//		
//		chk2.setOnAction((event)-> handlerChkAction()); //람다식
		
		btnExit.setOnAction((event) -> Platform.exit()); // 책처럼 하려면 메소드 만들어야 해

	} //initialize
	
	
	public void handlerChkAction() {
		String imgName = "";
		if(chk1.isSelected() && chk2.isSelected()) {
			imgName = "geek-glasses-hair.gif";
		} else if(chk1.isSelected()) {
			imgName = "geek-glasses.gif";
		} else if(chk2.isSelected()) {
			imgName = "geek-hair.gif";
		} else {
			imgName = "geek.gif";
		}
		checkImageView.setImage(new Image("/basic_images/" + imgName));
	}
	
} // 클래스
