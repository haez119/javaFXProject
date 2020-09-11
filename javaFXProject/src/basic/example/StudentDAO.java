package basic.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import basic.common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentDAO {
	
	Connection conn = ConnectionDB.getDB();
	
	public void deleteDB(String id) {
		
		String sql = "delete from chart where id = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void modfiyDB(Student st) {
		String sql = "update chart set name = ? , korean = ? , math = ? , english = ?  where id = ? ";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, st.getName());
			pstmt.setInt(2, st.getKorean());
			pstmt.setInt(3, st.getMath());
			pstmt.setInt(4, st.getEnglish());
			pstmt.setString(5, st.getId());
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addDB(Student st) {

		String sql = "insert into chart values(? , ? , ? , ? , ? )";
		ObservableList<Student> list;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, st.getId());
			pstmt.setString(2, st.getName());
			pstmt.setInt(3, st.getKorean());
			pstmt.setInt(4, st.getMath());
			pstmt.setInt(5, st.getEnglish());
			pstmt.executeUpdate();
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
	
	
	public ObservableList<Student> selectDB() {
		
		String sql = "select * from chart";
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
}
