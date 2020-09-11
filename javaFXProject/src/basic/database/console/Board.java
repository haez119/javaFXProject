package basic.database.console;

public class Board {
	
	String id;
	int korean;
	
	
	public Board(String id, int korean) {
		super();
		this.id = id;
		this.korean = korean;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}


}
