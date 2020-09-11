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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PeopleController implements Initializable {

	ObservableList<People> list = FXCollections.observableArrayList();
	@FXML TableView<People> boardView;
	@FXML TableColumn<People,?> peoId, peoName, peoNo;
	@FXML TextField txtId, txtName, txtNo;
	@FXML Button btnAdd, btnClose, btnDel;
	
	PeopleDAO dao = new PeopleDAO();
	
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
				People peo = new People(txtId.getText(), txtName.getText(), txtNo.getText());
				list.add(peo);
				dao.addDB(peo);
				boardView.setItems(dao.selectDB());
				txtId.clear(); txtName.clear(); txtNo.clear();
			}
		});
		
		//***********스테이지 닫기*********
		btnClose.setOnAction((e) -> {
				Stage stage = (Stage) btnClose.getScene().getWindow(); 
			    stage.close();
			});

	}
	
	
	
	

}
