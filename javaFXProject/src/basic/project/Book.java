package basic.project;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;



public class Book {
	private SimpleStringProperty bName;
	private SimpleStringProperty bWrite;
	private SimpleStringProperty bCompany;
	private SimpleIntegerProperty bPrice;
	private boolean yesNo;
	
	
	
	public boolean isYesNo() {
		return yesNo;
	}



	public void setYesNo(boolean yesNo) {
		this.yesNo = yesNo;
	}



	public Book(String bName, String bWrite, String bCompany, int bPrice, boolean yesNo) {
		super();
		this.bName = new SimpleStringProperty(bName);
		this.bWrite =  new SimpleStringProperty(bWrite);
		this.bCompany =  new SimpleStringProperty(bCompany);
		this.bPrice =  new SimpleIntegerProperty(bPrice);
		this.yesNo = yesNo;
	}
	

	public String getBName() {
		return this.bName.get();
	}


	public void setBName(String bName) {
		this.bName.set(bName);
	}


	public String getBWrite() {
		return this.bWrite.get();
	}


	public void setBWrite(String bWrite) {
		this.bWrite.set(bWrite);
	}


	public String getBCompany() {
		return this.bCompany.get();
	}


	public void setBCompany(String bCompany) {
		this.bCompany.set(bCompany);
	}


	public int getBPrice() {
		return this.bPrice.get();
	}


	public void setBPrice(int bPrice) {
		this.bPrice.set(bPrice);
	}
	
	
	

	
	
	
	
	

}
