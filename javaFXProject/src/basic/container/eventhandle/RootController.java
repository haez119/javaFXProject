package basic.container.eventhandle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class RootController implements Initializable {
	
	@FXML Label label;
	@FXML Slider slider;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number strartValue, Number endValue) {
				System.out.println("strart: " + strartValue.doubleValue());
				System.out.println("end: " + endValue.doubleValue());
				label.setFont(new Font(endValue.doubleValue()));
			}
		});
		
	}

}
