package basic.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RootController implements Initializable {
	
	ObservableList<Student> list;
	@FXML TableView<Student> tableView;
	@FXML Button btnAdd, btnBarChart;
	
	Stage primaryStage;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 칼럼 가져와서 tableView랑 연결하기
		TableColumn<Student, ?> tc = tableView.getColumns().get(0); 
		tc.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tc = tableView.getColumns().get(1); 
		tc.setCellValueFactory(new PropertyValueFactory<>("korean"));
		
		tc = tableView.getColumns().get(2); 
		tc.setCellValueFactory(new PropertyValueFactory<>("math"));
		
		tc = tableView.getColumns().get(3); 
		tc.setCellValueFactory(new PropertyValueFactory<>("english"));
		
		// Student 타입 list 불러와서 list 값을 tableView에 넣기
		list = FXCollections.observableArrayList();
		
		tableView.setItems(list);
		
		// 추가버튼
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				handleBtnAddAction();
				
			}
		});
		// 차트 버튼
		btnBarChart.setOnAction(e -> { handlebtnBarChartAction(); });

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
			
			btnFormCancel.setOnAction(e-> { 
				TextField txtName = (TextField) parent.lookup("#txtName");
				TextField txtKorean = (TextField) parent.lookup("#txtKorean");
				TextField txtMath = (TextField) parent.lookup("#txtMath");
				TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
				
				//txtName.clear(); txtKorean.clear(); txtMath.clear(); txtEnglish.clear();
				stage.close();
			});
			
			btnFormAdd.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// txtfield에 있는 값을 읽어와서
					TextField txtName = (TextField) parent.lookup("#txtName");
					TextField txtKorean = (TextField) parent.lookup("#txtKorean");
					TextField txtMath = (TextField) parent.lookup("#txtMath");
					TextField txtEnglish = (TextField) parent.lookup("#txtEnglish");
					
					// Student 생성자의 매개값으로 입력
					Student student = new Student(txtName.getText(), 
							Integer.parseInt(txtKorean.getText()), 
							Integer.parseInt(txtMath.getText()), 
							Integer.parseInt(txtEnglish.getText()));
					
					// 위에 선언한 list에 방금 저장한 값을 넣음
					list.add(student);
					// 넣고 나면 초기화
					txtName.clear(); txtKorean.clear(); txtMath.clear(); txtEnglish.clear(); 
					//stage.close(); 화면종료 주석처리
				}
			});
			
		} catch (IOException e) { e.printStackTrace();	}
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
			
			// 차트 가져와서 시리즈 추가하기
			BarChart barChart = (BarChart) chart.lookup("#barChart");
			
			// 국어점수
			XYChart.Series<String, Integer> seriesK = new XYChart.Series<>();
			seriesK.setName("국어");
			
			// 리스트에 있는 국어점수 다 담을거야
			ObservableList koreanList = FXCollections.observableArrayList();
			for(int i=0; i<list.size(); i++) {
				koreanList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getKorean()));
			}
			seriesK.setData(koreanList);
			barChart.getData().add(seriesK);
			
			XYChart.Series<String, Integer> serieM = new XYChart.Series<>();
			serieM.setName("수학");
			ObservableList mathList = FXCollections.observableArrayList();
			for(int i=0; i<list.size(); i++) {
				mathList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getMath()));
			}
			serieM.setData(mathList);
			barChart.getData().add(serieM);
			
			XYChart.Series<String, Integer> seriesE = new XYChart.Series<>();
			seriesE.setName("영어");
			ObservableList englishList = FXCollections.observableArrayList();
			for(int i=0; i<list.size(); i++) {
				englishList.add(new XYChart.Data<>(list.get(i).getName(), list.get(i).getMath()));
			}
			seriesE.setData(englishList);
			barChart.getData().add(seriesE);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
