package basic.project;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookListController implements Initializable {

	ObservableList<Book> list = FXCollections.observableArrayList();
	@FXML
	TableView<Book> tableView;
	@FXML
	TextField txtBN, txtBW, txtBC, txtBP;
	@FXML
	Button btnAdd, btnClose, btnPeoAdd, btnDel, btnRent, btnPrev, btnNext;
	@FXML ImageView imageView;

	BookDAO dao = new BookDAO();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn<Book, ?> tc = (TableColumn<Book, ?>) tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("bName"));

		tc = (TableColumn<Book, ?>) tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("bWrite"));

		tc = (TableColumn<Book, ?>) tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("bCompany"));

		tc = (TableColumn<Book, ?>) tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("bPrice"));

		tc = (TableColumn<Book, ?>) tableView.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("yesNo"));
		
		tableView.setItems(dao.selectDB());
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				// 이미지 넣기
				String name = tableView.getSelectionModel().getSelectedItem().getBName();
				imageView.setImage(new Image("basic_images/" + name + ".png"));
				
//				tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
//
//					@Override
//					public void changed(ObservableValue<? extends Book> arg0, Book oldV, Book newV) {
//						imageView.setImage(new Image("basic_images/" + newV.getBName() + ".png"));
//					}
//				});
				
				if (event.getClickCount() == 2) {
					handleDoubleClickAction(name, tableView.getSelectionModel().getSelectedItem().isYesNo());
				}
			}

		});
		
		btnNext.setOnAction(a -> tableView.getSelectionModel().selectNext());
		btnPrev.setOnAction(a -> tableView.getSelectionModel().selectPrevious());

		btnAdd.setOnAction((e) -> {
			Book book = new Book(txtBN.getText(), txtBW.getText(), txtBC.getText(), Integer.parseInt(txtBP.getText()),
					true);
			list.add(book);
			dao.addDB(book);
			tableView.setItems(dao.selectDB());
			txtBN.clear();
			txtBW.clear();
			txtBC.clear();
			txtBP.clear();
		});

		btnClose.setOnAction((e) -> Platform.exit());

		btnPeoAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Stage stage = new Stage(StageStyle.UTILITY);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(btnPeoAdd.getScene().getWindow());

				try {
					Parent parent = FXMLLoader.load(getClass().getResource("PeopleAdd.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
					stage.setTitle("회원정보");
					
					

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		btnDel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String name = tableView.getSelectionModel().getSelectedItem().getBName();
				dao.deleteDB(name);
				tableView.setItems(dao.selectDB());
			}
		});

		btnRent.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Stage stage = new Stage(StageStyle.UTILITY);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(btnRent.getScene().getWindow());

				try {
					Parent parent = FXMLLoader.load(getClass().getResource("RentList.fxml"));
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
					stage.setTitle("대출 목록");

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

	}

	public void handleDoubleClickAction(String name, boolean ys) {

		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(tableView.getScene().getWindow());
		stage.setTitle("대출정보");

		try {
			
			Parent parent = FXMLLoader.load(getClass().getResource("Lend.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();

			TextField txtBName = (TextField) parent.lookup("#txtBName");
			TextField txtBWrite = (TextField) parent.lookup("#txtBWrite");
			TextField txtBCompany = (TextField) parent.lookup("#txtBCompany");

			TextField txtId = (TextField) parent.lookup("#txtId");
			TextField txtName = (TextField) parent.lookup("#txtName");
			TextField txtNo = (TextField) parent.lookup("#txtNo");

			TextField txtLendDate = (TextField) parent.lookup("#txtLendDate");
			TextField txtReturnDate = (TextField) parent.lookup("#txtReturnDate");

			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			txtLendDate.setText(sdf.format(now));
			txtLendDate.setEditable(false);
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.DATE, 14);
			
			sdf = new SimpleDateFormat("yyyy-MM-dd"); // 2주 뒤로 하고 싶어
			txtReturnDate.setText(sdf.format(cal.getTime()));
			txtReturnDate.setEditable(false);
			
			
			list = dao.selectDB();
			
			// 텍스트 필드에 도서정보 넣어놓기 , 수정불가
			for (Book b : list) {
				if (name.equals(b.getBName())) {
					txtBName.setText(name);
					txtBName.setEditable(false);
					txtBWrite.setText(b.getBWrite());
					txtBWrite.setEditable(false);
					txtBCompany.setText(b.getBCompany());
					txtBCompany.setEditable(false);

				}
			}

			Button btnReturn = (Button) parent.lookup("#btnReturn");
			Button btnRent = (Button) parent.lookup("#btnRent");
			Button btnCancle = (Button) parent.lookup("#btnCancle");

			btnCancle.setOnAction(a -> stage.close());

			BookRentDAO brDAO = new BookRentDAO();
			
			// 대출버튼 누르면
			btnRent.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					PeopleController pc = new PeopleController();
					pc.list = pc.dao.selectDB();
					
					if(ys) {
						
						boolean bb = true;
						for (People po : pc.list) {
							if (po.getId().equals(txtId.getText()) && 
								po.getName().equals(txtName.getText())&& 
								po.getNumber().equals(txtNo.getText())) {
								
								bb = true;
								break;
							} else {
								bb=false;
							}
						}
						
						if(bb) {
							BookRent br = new BookRent(txtId.getText(), 
									   txtBName.getText(), 
									   txtLendDate.getText(), 
									   txtReturnDate.getText());

							brDAO.addDB(br);
							dao.modfiyDB(txtBName.getText(), false);
							tableView.setItems(dao.selectDB());
							stage.close();
						} else {
							showPopup("등록된 회원이 없습니다.");
						}

					} else {
						showPopup("대출된 책입니다.");
					}
				}

			});
			
			// 반납버튼 누르면 
			btnReturn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					if(ys) {
						showPopup("반납된 책입니다.");
					} else {
						
						BookRentDAO brDAO = new BookRentDAO();
						ObservableList<BookRent> list = brDAO.selectDB();

						boolean bb = true;
						
						// 대출테이블 id랑 txtId랑 같아야 반납되게
						
						for (BookRent br : list) {
							if (br.getPeoId().equals(txtId.getText()) && br.getBookName().equals(txtBName.getText())) {
								bb = true;
								break;
							} else {
								bb=false;
							}
						}
						
						if(bb) {
							brDAO.deleteDB(txtId.getText(), txtBName.getText());
							dao.modfiyDB(txtBName.getText(), true);
							tableView.setItems(dao.selectDB());
							stage.close();
						}else {
							showPopup("회원 정보가 일치하지 않습니다.");
						}
					}
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
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
		hbox.setOnMouseClicked (event -> pop.hide() ); // hbox 선택하면 pop 사라짐
		pop.show(btnAdd.getScene().getWindow()); 
		
		// main아니라서 윈도우 못가져옴 > 가져오는 법 아무 컨트롤의 씬의 윈도우를 가져와라

	}


}
