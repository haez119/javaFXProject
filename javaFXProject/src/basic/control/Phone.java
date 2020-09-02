package basic.control;

import javafx.beans.property.SimpleStringProperty;

public class Phone {
	
	private SimpleStringProperty smartPhone; // 문자를 감싸고 속성값을 가짐?
	private SimpleStringProperty image;
	
	public Phone(String smartPhone, String image) {
		
		this.smartPhone = new SimpleStringProperty(smartPhone);
		this.image = new SimpleStringProperty(image);
	}
	
	public void setSmartPhone(String smartPhone) {
		this.smartPhone.set(smartPhone);
	}
	
	public String getSmartPhone() {
		return smartPhone.get();
	}

	public void setImage(String image) {
		this.image.set(image);
	}

	public String getImage() {
		return image.get();
	}

	
	
	
	
}
