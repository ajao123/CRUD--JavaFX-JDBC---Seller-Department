package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable{

	@FXML
	MenuItem menuItemSeller;
	
	@FXML
	MenuItem menuItemDepartment;
	
	@FXML
	MenuItem menuItemAbout;
	
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml", (SellerListController controller) ->{
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}
	
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) ->{
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}
	
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x ->{});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newBox = loader.load();
			
			//Carrega a Scene principal
			Scene mainScene = Main.getMainScene();	
			
			//Guarda o mainVBox do  Scene Principal
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			//Guarda o menu presente no mainVBox
			Node mainMenu = mainVBox.getChildren().get(0);
			
			//Apaga tudo que está na Scene Principal
			mainVBox.getChildren().clear();
			
			//Guarda o menu do Scene Principal
			mainVBox.getChildren().add(mainMenu);
			
			//Guarda o novo VBox
			mainVBox.getChildren().addAll(newBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);			
			
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	
	}

}
