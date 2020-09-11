package basic.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import basic.common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookRentDAO {
	
	
	Connection conn = ConnectionDB.getDB();
	
	public void addDB(BookRent br) {

		String sql = "insert into Book_Rent values(? , ? , ? , ?)";
		ObservableList<BookRent> list;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, br.getPeoId());
			pstmt.setString(2, br.getBookName());
			pstmt.setString(3, br.getRentDate());
			pstmt.setString(4, br.getReturnDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	
	public ObservableList<BookRent> selectDB() {
		
		String sql = "select * from Book_Rent";
		ObservableList<BookRent> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 쿼리실행, 쿼리 결과가 rs에 담김
			while (rs.next()) {
				BookRent bk = new BookRent(rs.getString("peo_id"), 
								   rs.getString("book_name"), 
								   rs.getString("rent_date"), 
								   rs.getString("return_date"));  // 칼럼이름
				list.add(bk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void deleteDB(String id, String name) {
		
		String sql = "delete from Book_Rent where peo_id = ? AND book_name = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
		}
	}
	
	

}
