package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/MainWindowView.fxml"));
		Scene mainScene = new Scene(loader.load());
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
	}
	
}
