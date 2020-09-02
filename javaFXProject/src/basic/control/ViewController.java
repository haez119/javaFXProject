package basic.control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ViewController implements Initializable {

	// listView, tableView, imageView

	@FXML
	ListView<String> listView;
	@FXML
	TableView<Phone> tableView;
	@FXML
	ImageView imageView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> list = FXCollections.observableArrayList();
		// ObservableList<String> arraylist와 비슷? 변환되는 값을 체크??????
		list.add("갤럭시s1");
		list.add("갤럭시s2");
		list.add("갤럭시s3");
		list.add("갤럭시s4");
		list.add("갤럭시s5");
		list.add("갤럭시s6");
		list.add("갤럭시s7");

		listView.setItems(list); // listView에 아이템으로 list 값을 넣음
		
		// listview 선택하면 같은  tableview 도 선택되게
		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
				tableView.getSelectionModel().select(newValue.intValue());
				tableView.scrollTo(newValue.intValue());
			}

		});

		ObservableList<Phone> phoneList = FXCollections.observableArrayList();
		phoneList.add(new Phone("갤럭시s1", "phone01.png"));
		phoneList.add(new Phone("갤럭시s2", "phone02.png"));
		phoneList.add(new Phone("갤럭시s3", "phone03.png"));
		phoneList.add(new Phone("갤럭시s4", "phone04.png"));
		phoneList.add(new Phone("갤럭시s5", "phone05.png"));
		phoneList.add(new Phone("갤럭시s6", "phone06.png"));
		phoneList.add(new Phone("갤럭시s7", "phone07.png"));

		// Phone 칼럼이랑 fxml 칼럼이랑 연결해주기 매핑

		// tableView의 칼럼을 가져오려면 TableColumn 사용 ?? < 폰, ? > 폰 타입의 객체가 ?에 저장됨?
		TableColumn<Phone, ?> tcSmartPhone = tableView.getColumns().get(0);
		tcSmartPhone.setCellValueFactory(new PropertyValueFactory<>("smartPhone"));

		TableColumn<Phone, ?> tcImage = tableView.getColumns().get(1);
		tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));
		tcSmartPhone.setStyle("-fx-alignment: CENTER;");
		tcImage.setStyle("-fx-alignment: CENTER;");

		tableView.setItems(phoneList);
		
		// tableview 선택할 때 이미지 변경되게
		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {

			@Override
			public void changed(ObservableValue<? extends Phone> arg0, Phone oldV, Phone newV) {
				imageView.setImage(new Image("basic_images/" + newV.getImage()));
			}
			
		});
		

	}

}
