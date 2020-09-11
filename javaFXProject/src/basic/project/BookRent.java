package basic.project;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;

public  class  BookRent {
	private SimpleStringProperty peoId;
	private SimpleStringProperty bookName;
	private SimpleStringProperty rentDate;
	private SimpleStringProperty returnDate;
	
	public BookRent() {}

	public BookRent(String peoId, String bookName, String rentDate, String returnDate) {
		super();
		this.peoId = new SimpleStringProperty(peoId);
		this.bookName = new SimpleStringProperty(bookName);
		this.rentDate = new SimpleStringProperty(rentDate);
		this.returnDate = new SimpleStringProperty(returnDate);
	}
	
	
	public String getPeoId() {
		return this.peoId.get();
	}
	public void setPeoId(String peoId) {
		this.peoId.set(peoId);
	}
	public String getBookName() {
		return this.bookName.get();
	}
	public void setBookName(String bookName) {
		this.bookName.set(bookName);
	}
	public String getRentDate() {
		return this.rentDate.get();
	}
	public void setRentDate(String rentDate) {
		this.rentDate.set(rentDate);
	}
	public String getReturnDate() {
		
		return this.returnDate.get();
	}
	public void setReturnDate(String returnDate) {
		this.returnDate.set(returnDate);
	}
	
	
	
	
	
	
	

}
