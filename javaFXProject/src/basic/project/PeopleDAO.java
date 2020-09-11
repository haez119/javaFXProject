package basic.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import basic.common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PeopleDAO {
	
	Connection conn = ConnectionDB.getDB();
	
	public void addDB(People pp) {

		String sql = "insert into people values(? , ? , ?)";
		ObservableList<People> list;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pp.getId());
			pstmt.setString(2, pp.getName());
			pstmt.setString(3, pp.getNumber());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public ObservableList<People> selectDB() {
		
		String sql = "select * from people";
		ObservableList<People> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 쿼리실행, 쿼리 결과가 rs에 담김
			while (rs.next()) {
				People pp = new People(rs.getString("id"), 
								   rs.getString("name"), 
								   rs.getString("no"));  // 칼럼이름
				list.add(pp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void deleteDB(String id) {
		
		String sql = "delete from people where id = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
