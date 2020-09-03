package basic.control;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BoardController implements Initializable {

	@FXML
	TableView<Board> boardView;
	@FXML
	TextField txtTitle;
	@FXML
	ComboBox<String> comboPublic;
	@FXML
	TextField txtExitDate;
	@FXML
	TextArea txtContent;
	@FXML
	Button btnNext, btnPrev;

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		TableColumn<Board, String> tcTitle = new TableColumn<>("제목"); // 보여지는 이름
		tcTitle.setCellValueFactory(new PropertyValueFactory<>("title")); // 칼럼이름
		tcTitle.setPrefWidth(80);
		boardView.getColumns().add(tcTitle); // boardView에 만든 타이틀 칼럼 붙여줌
		boardView.setItems(getBoardList());

		TableColumn<Board, String> tcPublicity = new TableColumn<>("공개여부");
		tcPublicity.setCellValueFactory(new PropertyValueFactory<>("publicity"));
		tcPublicity.setPrefWidth(80);
		boardView.getColumns().add(tcPublicity);
		boardView.setItems(getBoardList());

		// 리스트 클릭하면 옆에 상세내용 나오게 하기
		boardView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Board>() {
			@Override
			public void changed(ObservableValue<? extends Board> observable, Board o, Board n) {
				txtTitle.setText(n.getTitle());
				comboPublic.setValue(n.getPublicity());
				txtExitDate.setText(n.getExitDate());
				txtContent.setText(n.getContent());
			}
		});
		
		btnNext.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent a) {
				
			}
		});
		
		
		
	}

	public ObservableList<Board> getBoardList() {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr", passwd = "hr";
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String sql = "select * from new_board order by 1";
		ObservableList<Board> list = FXCollections.observableArrayList();
		// sql 결과물을 list에 담을거야
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board(rs.getString("title"), rs.getString("password"), rs.getString("publicity"),
						rs.getString("exit_Date"), rs.getString("content"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
