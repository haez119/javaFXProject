package basic.project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PeopleController implements Initializable {

	ObservableList<People> list = FXCollections.observableArrayList();
	@FXML TableView<People> boardView;
	@FXML TableColumn<People,?> peoId, peoName, peoNo;
	@FXML TextField txtId, txtName, txtNo;
	@FXML Button btnAdd, btnClose, btnDel;
	
	PeopleDAO dao = new PeopleDAO();
	BookListController bc = new BookListController();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		TableColumn<People, ?> tc = (TableColumn<People, ?>) boardView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		tc = (TableColumn<People, ?>) boardView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tc = (TableColumn<People, ?>) boardView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("number"));
		
		boardView.setItems(dao.selectDB());
		
		
		btnDel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String id = boardView.getSelectionModel().getSelectedItem().getId();
				dao.deleteDB(id);
				boardView.setItems(dao.selectDB());
			}
			
		});
				

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if (txtId.getText() == null || txtId.getText().equals("")) {
					showPopup("아이디를 입력하세요");
				} else if(txtName.getText() == null || txtName.getText().equals("")) {
					showPopup("이름을 입력하세요");
				} else if(txtNo.getText() == null || txtNo.getText().equals("")) {
					showPopup("전화번호를 입력하세요");
				} else {
					People peo = new People(txtId.getText(), txtName.getText(), txtNo.getText());
					list.add(peo);
					dao.addDB(peo);
					boardView.setItems(dao.selectDB());
					txtId.clear(); txtName.clear(); txtNo.clear();
				}

				
			}
		});
		
		//***********스테이지 닫기*********
		btnClose.setOnAction((e) -> {
				Stage stage = (Stage) btnClose.getScene().getWindow(); 
			    stage.close();
			});

	}
	
	public void showPopup(String msg) {

		HBox hbox = new HBox(); // 컨테이너
		hbox.setStyle("-fx-background-color: gray; -fx-background-radius: 10;");
		hbox.setAlignment(Pos.CENTER);

		ImageView iv = new ImageView(); // 컨트롤
		iv.setImage(new Image("basic_images/dialog-info.png"));
		Label label = new Label();
		label.setText(msg);
		label.setStyle("-fx-text-fill: white; ");
		hbox.getChildren().addAll(iv, label);
		Popup pop = new Popup(); // 스테이지에 있는 (컨트롤이 있어야 해)
		pop.getContent().add(hbox); 
		
		//pop.getOnAutoHide();  // 다른 창 선택시 자동 닫힘?
		
		hbox.setOnMouseClicked (event -> pop.hide() ); // hbox 선택하면 pop 사라짐
		pop.show(btnClose.getScene().getWindow()); 
		
		// main아니라서 윈도우 못가져옴 > 가져오는 법 아무 컨트롤의 씬의 윈도우를 가져와라

	}
	
	
	

}
