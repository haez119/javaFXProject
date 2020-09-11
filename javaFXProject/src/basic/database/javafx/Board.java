package basic.database.javafx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Board {
	
	SimpleStringProperty id;
	SimpleIntegerProperty korean;

	public Board(String id, int korean) {
		super();
		this.id = new SimpleStringProperty(id);
		this.korean = new SimpleIntegerProperty(korean);
	}

	public String getId() {
		return this.id.get();
	}

	public void setId(String id) {
		this.id.set(id);
	}

	public int getKorean() {
		return this.korean.get();
	}

	public void setKorean(int korean) {
		this.korean.set(korean);
	}

}
