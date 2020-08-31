package basic.container;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class FruitExample extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		TilePane tp = new TilePane();
		tp.setPrefTileHeight(100);
		tp.setPrefTileWidth(100);
		
//		ImageView iv1 = new ImageView();
//		iv1.setImage(new Image("/basic_images/fruit1.jpg"));
//		
//		ImageView iv2 = new ImageView();
//		iv2.setImage(new Image("/basic_images/fruit2.jpg"));
//		
//		ImageView iv3 = new ImageView();
//		iv3.setImage(new Image("/basic_images/fruit3.jpg"));
//		
//		ImageView iv4 = new ImageView();
//		iv4.setImage(new Image("/basic_images/fruit4.jpg"));
		
//		ImageView iv5 = new ImageView();
//		iv5.setImage(new Image("/basic_images/fruit5.jpg"));
//		tp.getChildren().add(iv1);
//		tp.getChildren().add(iv2);
//		tp.getChildren().add(iv3);
//		tp.getChildren().add(iv4);
//		tp.getChildren().add(iv5);
		
		// 배열로 넣기
		
		ImageView[] ary = new ImageView[5];
		
		for(int i=0; i<5 ; i++) {
			ImageView iv = new ImageView();
			iv.setImage(new Image("/basic_images/fruit" + (i+1) + ".jpg"));
			ary[i] = iv;
			tp.getChildren().add(ary[i]);
		}
		
		Scene scene = new Scene(tp);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("TilePane 예제");
		
		
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}

