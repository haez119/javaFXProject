package basic.project;

import javafx.beans.property.SimpleStringProperty;

public class People {
	private SimpleStringProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty number;
	
	public People(String id, String name, String number) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.number = new SimpleStringProperty(number);
	}

	public String getId() {
		return this.id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public String getName() {
		return this.name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getNumber() {
		return this.number.get();
	}

	public void setNumber(String number) {
		this.number.set(number);
	}
	
	
	
	
	

}
