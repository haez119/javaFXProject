package basic.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import basic.common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookDAO {
	
	Connection conn = ConnectionDB.getDB();
	
	public void modfiyDB(String name, boolean ys) {
		String sql = "update Book set book_lend = ? where book_name = ? ";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setBoolean(1, ys);
			pstmt.setString(2, name);
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void addDB(Book bk) {

		String sql = "insert into book values(? , ? , ? , ? , ?)";
		ObservableList<Book> list;

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bk.getBName());
			pstmt.setString(2, bk.getBWrite());
			pstmt.setString(3, bk.getBCompany());
			pstmt.setInt(4, bk.getBPrice());
			pstmt.setBoolean(5, bk.isYesNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}
	
	public ObservableList<Book> selectDB() {
		
		String sql = "select * from book order by 5";
		ObservableList<Book> list = FXCollections.observableArrayList();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 쿼리실행, 쿼리 결과가 rs에 담김
			while (rs.next()) {
				Book bk = new Book(rs.getString("book_name"), 
								   rs.getString("book_write"), 
								   rs.getString("book_company"), 
								   rs.getInt("book_price"),
								   rs.getBoolean("book_lend"));  // 칼럼이름
				list.add(bk);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void deleteDB(String name) {
		
		String sql = "delete from book where book_name = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			int r = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}