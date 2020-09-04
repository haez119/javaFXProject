package basic.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import basic.common.ConnectionDB;
import basic.control.Board;
import basic.exam22.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.util.AnchorCertificates;

public class RootController implements Initializable {

	ObservableList<Student> list;
	@FXML
	TableView<Student> tableView;
	@FXML
	Button btnAdd, btnBarChart, btnSelect, btnDelete;

	Stage primaryStage;
	
	

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		// 칼럼 가져와서 tableView랑 연결하기
		TableColumn<Student, ?> tc = tableView.getColumns().get(0);
		tc.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		tc = tableView.getColumns().get(1);
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tc = tableView.getColumns().get(2);
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));

		tc = tableView.getColumns().get(3);
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));

		tc = tableView.getColumns().get(4);
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		
		

		// Student 타입 list 불러와서 list 값을 tableView에 넣기

		btnSelect.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tableView.setItems(list);
				list = updateDB(); 
			}
		});

		// 추가버튼
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleBtnAddAction();

			}
		});
		// 차트 버튼
		btnBarChart.setOnAction(e -> {
			
			handlebtnBarChartAction();
		});

		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) { // 2면 더블클릭
					String seletedId = tableView.getSelectionModel().getSelectedItem().getId(); 
					handleDoubleClickAction(seletedId);
				} 
			}
		});
		
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String id = tableView.getSelectionModel().selectedItemProperty().getValue().getId();
				deleteDB(id);
				
			}
		});
		
		

	}

	public void handleDoubleClickAction(String id) {
		Stage stage = new Stage(StageStyle.DECORATED.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);

		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(210, 230);

		Label lName, lKorean, lMath, lEnglish;
		TextField tId, tName, tKorean, tMath, tEnglish;
		Button btnUpdate = new Button("수정");
		btnUpdate.setLayoutX(85);
		btnUpdate.setLayoutY(184);
		
		lName = new Label("이름");
		lName.setLayoutX(35);
		lName.setLayoutY(50);

		lKorean = new Label("국어");
		lKorean.setLayoutX(35);
		lKorean.setLayoutY(73);

		lMath = new Label("수학");
		lMath.setLayoutX(35);
		lMath.setLayoutY(99);

		lEnglish = new Label("영어");
		lEnglish.setLayoutX(35);
		lEnglish.setLayoutY(132);

		tId = new TextField();
		tId.setPrefWidth(110);
		tId.setLayoutX(72);
		tId.setLayoutY(30);
		tId.setText(id); // 네임 필드에 매개값 넣기
		tId.setEditable(false); // 수정 불가
		
		tName = new TextField();
		tName.setPrefWidth(110);
		tName.setLayoutX(72);
		tName.setLayoutY(50);

		tKorean = new TextField();
		tKorean.setPrefWidth(110);
		tKorean.setLayoutX(72);
		tKorean.setLayoutY(69);

		tMath = new TextField();
		tMath.setPrefWidth(110);
		tMath.setLayoutX(72);
		tMath.setLayoutY(95);

		tEnglish = new TextField();
		tEnglish.setPrefWidth(110);
		tEnglish.setLayoutX(72);
		tEnglish.setLayoutY(128);
		
		for (Student stu : list) {
			if (stu.getId().equals(id)) {
				// 값을 가져옴
				tName.setText(stu.getName());
				tKorean.setText(String.valueOf(stu.getKorean()));
				tMath.setText(String.valueOf(stu.getMath())); // getMath는 숫자 setText는 문자
				tEnglish.setText(String.valueOf(stu.getEnglish()));
			}
		}
		
		ap.getChildren().addAll(tName, tKorean, tMath, tEnglish, lKorean, lMath, lEnglish, btnUpdate, lName, tId);

		Scene scene = new Scene(ap);
		stage.setScene(scene);
		stage.show();
		
		btnUpdate.setOnAction(a -> {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId().equals(id)) {
					Student student = new Student(id, tName.getText(), Integer.parseInt(tKorean.getText()),
							Integer.parseInt(tMath.getText()), Integer.parseInt(tEnglish.getText()));

					list.set(i, student);
					modfiyDB(student);
				}
			}
			stage.close();
		});

	}

	// 추가화면 보여주는 작업 AddForm.fxml
	public void handleBtnAddAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL); // 모달폼(종속되는)으로 만들거야
		stage.initOwner(btnAdd.getScene().getWindow()); // btnAdd의 신의 윈도우에 종속

		try {
			// Parent 컨테이너의 모든 타입 받을 수 있음
			Parent parent = FXMLLoader.load(getClass().getResource("AddForm.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
			// 추가화면 컨트롤 사용
			Button btnFormAdd = (Button) parent.lookup("#btnFormAdd"); // 추가
			Button btnFormCancel = (Button) parent.lookup("#btnFormCancel"); // 취소(내용 초기화하기)

			btnFormCancel.setOnAction(e -> {
				TextField txtId = (TextField) parent.lookup("#txtId");
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtKorean = (TextField) parent.lookup("#txtKorean");
				TextField txtMath = (TextField) parent.lookup("#txtMath");
				TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");

				stage.close();
			});

			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// txtfield에 있는 값을 읽어와서
					TextField txtId = (TextField) parent.lookup("#txtId");
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");

					// Student 생성자의 매개값으로 입력
					Student student = new Student(txtId.getText(), txtName.getText(),
							Integer.parseInt(txtKorean.getText()),
							Integer.parseInt(txtMath.getText()), 
							Integer.parseInt(txtEnglish.getText()));

					// 위에 선언한 list에 방금 저장한 값을 넣음
					list.add(student);
					addDB(student); // 디비에 추가
					txtId.clear(); txtName.clear(); txtKorean.clear(); txtMath.clear(); txtEnglish.clear();
				}
			});
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 차트버튼
	// 폼 종속 경로?? 다르게 설정하기(메소드)
	public void handlebtnBarChartAction() {
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage); // 메소드 이용
		

		try {
			Parent chart = FXMLLoader.load(getClass().getResource("BarChart.fxml"));
			Scene scene = new Scene(chart);
			stage.setScene(scene);
			stage.show();
			
			Button btnClose = (Button) chart.lookup("#btnClose");
			btnClose.setOnAction(a -> {
				stage.close();
			});

			// BarChar.fxml에 id가 barChar인걸 찾아서 barchart에 대입?
			BarChart barChart = (BarChart) chart.lookup("#barChart");

			XYChart.Series<String, Integer> seriesK = new XYChart.Series<>(); // 시리즈 셍성
			seriesK.setName("국어");
			ObservableList<XYChart.Data<String, Integer>> koreanList = FXCollections.observableArrayList(); // 리스트 생성
			for (int i = 0; i < list.size(); i++) { // 리스트에 점수 담기
				koreanList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getKorean()));
			}
			seriesK.setData(koreanList); // 시리즈에 리스트 담기
			barChart.getData().add(seriesK); // 차트에 시리즈 담기

			XYChart.Series<String, Integer> seriesM = new XYChart.Series<>();
			seriesM.setName("수학");
			ObservableList<XYChart.Data<String, Integer>> mathList = FXCollections.observableArrayList();
			for (int i = 0; i < list.size(); i++) {
				mathList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getMath()));
			}
			seriesM.setData(mathList);
			barChart.getData().add(seriesM);

			XYChart.Series<String, Integer> seriesE = new XYChart.Series<>();
			seriesE.setName("영어");
			ObservableList<XYChart.Data<String, Integer>> englishList = FXCollections.observableArrayList();
			for (int i = 0; i < list.size(); i++) {
				englishList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getMath()));
			}
			seriesE.setData(englishList);
			barChart.getData().add(seriesE);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ObservableList<Student> updateDB() {
		Connection conn = ConnectionDB.getDB();
		String sql = "select * from chart order by 1";
		ObservableList<Student> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 쿼리실행, 쿼리 결과가 rs에 담김
			while (rs.next()) {
				Student st = new Student(rs.getString("id"), rs.getString("name"), 
						Integer.parseInt(rs.getString("korean")), 
						Integer.parseInt(rs.getString("math")), 
						Integer.parseInt(rs.getString("english")));  // 칼럼이름
				list.add(st);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void addDB(Student st) {
		
		Connection conn = ConnectionDB.getDB();
		String sql = "insert into chart values(? , ? , ? , ? , ? )";
		
		try {
			PreparedStatement pstmt = conn .prepareStatement(sql);
			pstmt.setString(1,st.getId());
			pstmt.setString(2,st.getName());
			pstmt.setInt(3,st.getKorean());
			pstmt.setInt(4,st.getMath());
			pstmt.setInt(5,st.getEnglish());
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건 입력됨");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void modfiyDB(Student st) {
		Connection conn = ConnectionDB.getDB();
		String sql = "update chart set name = ? , korean = ? , math = ? , english = ?  where id = ? ";
		
		try {
			PreparedStatement pstmt = conn .prepareStatement(sql);
			pstmt.setString(1, st.getName());
			pstmt.setInt(2, st.getKorean());
			pstmt.setInt(3, st.getMath());
			pstmt.setInt(4, st.getEnglish());
			pstmt.setString(5, st.getId());
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건 수정됨");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteDB(String id) {
		Connection conn = ConnectionDB.getDB();
		String sql = "delete from chart where id = ?";
		
		try {
			PreparedStatement pstmt = conn .prepareStatement(sql);
			pstmt.setString(1, id);
			int r = pstmt.executeUpdate();
			System.out.println(r + " 건 삭제됨");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
