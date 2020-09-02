package basic.control;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InputController implements Initializable {

	@FXML TextField txtTitle;
	@FXML PasswordField txtPassword;
	@FXML ComboBox<String> comboPublic;
	@FXML DatePicker dateExit;
	@FXML TextArea txtContent;
	@FXML Button btnReg, btnCancel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		// 초기값으로 넣어주기
//		txtTitle.setText("안녕하세요");
//		comboPublic.setValue("public");
		btnReg.setOnAction((event) -> handleBtnRegAction());

	} // initialize

	public void handleBtnRegAction() {
		// 널이거나 값이 없으면 팝업창 띄우기
		if (txtTitle.getText() == null || txtTitle.getText().equals("")) {
			showPopup("타이틀을 입력하세요");
		} else if(txtPassword.getText() == null || txtPassword.getText().equals("")) {
			showPopup("패스워드를 입력하세요");
		} else if(comboPublic.getValue() == null || comboPublic.getValue().equals("")) {
			showPopup("공개 여부를 지정하세요");
		} else if (dateExit.getValue() == null) {
			showCustomDialog("날짜를 입력하세요");
		} else {
			// 디비에 등록
			insetDate();
		}

	}
	
	public void insetDate() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr", passwd = "hr";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		String sql = "insert into new_board values( ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = conn .prepareStatement(sql);
			pstmt.setString(1, txtTitle.getText()); // 첫번째 파라미터에 title넣기
			pstmt.setString(2, txtPassword.getText());
			pstmt.setString(3, comboPublic.getValue());
			pstmt.setString(4, dateExit.getValue().toString());
			pstmt.setString(5, txtContent.getText());
			
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
	
	public void showCustomDialog(String msg) {
		
		Stage stage = new Stage(StageStyle.UTILITY); // utility x버튼만 (스테이지의 스타일은 최소화 최대화 등등을 말함)
		stage.initModality(Modality.WINDOW_MODAL); // 창이 열리면 뒤의 다른창이 선택 안됨 (여부 결정하는 메소드)
		stage.initOwner(btnReg.getScene().getWindow());// 어느 창에 종속 될 것이냐 >> btnReg창에 종속됨
		
		// 컨테이너
		AnchorPane ap = new AnchorPane();
		ap.setPrefSize(400,150); //넓이 높이 같이
		// 컨트롤
		ImageView iv = new ImageView();
		iv.setImage(new Image("basic_images/dialog-info.png"));
		iv.setFitWidth(50); iv.setFitHeight(50);
		iv.setLayoutX(15); iv.setLayoutY(15);
		iv.setPreserveRatio(true); // 가로세로 늘릴 때 같이 늘리도록
		
		Button btnOk = new Button("확인");
		btnOk.setLayoutX(336); btnOk.setLayoutY(104);
		btnOk.setOnAction((e) -> stage.close());
		
		Label label = new Label(msg);
		label.setLayoutX(87); label.setLayoutY(33); label.setPrefSize(290, 15);
		
		ap.getChildren().addAll(iv, btnOk, label); // 컨테이너에 컨트롤 붙이기
		
		Scene scene = new Scene(ap); stage.setScene(scene); // 씬에 컨테이너 붙이기
		
		stage.show(); // 위에서 stage.initOwner 이용해서 지정 해줬기 때문에 그냥 show();
	}

	public void showPopup(String msg) {

		HBox hbox = new HBox(); // 컨테이너
		hbox.setStyle("-fx-background-color: black; -fx-background-radius: 20;");
		hbox.setAlignment(Pos.CENTER);

		ImageView iv = new ImageView(); // 컨트롤
		iv.setImage(new Image("basic_images/dialog-info.png"));
		Label label = new Label();
		label.setText(msg);
		label.setStyle("-fx-text-fill: yellow; ");
		hbox.getChildren().addAll(iv, label);
		Popup pop = new Popup(); // 스테이지에 있는 (컨트롤이 있어야 해)
		pop.getContent().add(hbox);
		pop.setAutoHide(true); // 다른창 선택시 자동으로 없어짐
		pop.show(btnReg.getScene().getWindow()); // main아니라서 윈도우 못가져옴 > 가져오는 법 아무 컨트롤의 씬의 윈도우를 가져와라

	}
}
