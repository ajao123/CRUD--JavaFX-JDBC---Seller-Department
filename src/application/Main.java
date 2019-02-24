package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane scrollPane = loader.load();
			mainScene = new Scene(scrollPane);
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true);
			
			primaryStage.setScene(mainScene);		//Palco da Cena
			primaryStage.setTitle("Sample JavaFX application");	//Titulo do Palco
			primaryStage.show();	//Mostrar o Palco
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
}
