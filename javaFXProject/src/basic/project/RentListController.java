package basic.project;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RentListController implements Initializable{
	
	@FXML TableView<BookRent> tableView;
	@FXML TableColumn<BookRent, ?> peoId, bookName, bookRent, bookReturn;
	@FXML Button btnOk;
	
	BookRentDAO dao = new BookRentDAO();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		TableColumn<BookRent, ?> tc = (TableColumn<BookRent, ?>) tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("peoId"));

		tc = (TableColumn<BookRent, ?>) tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("bookName"));

		tc = (TableColumn<BookRent, ?>) tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("rentDate"));

		tc = (TableColumn<BookRent, ?>) tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		
		tableView.setItems(dao.selectDB());
		
		btnOk.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				Stage stage = new Stage();
				stage = (Stage) btnOk.getScene().getWindow();
				stage.close();
				
			}
		});
		
		
	}
	
	

}
