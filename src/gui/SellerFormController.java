package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.entities.Seller;
import model.listeners.DataChangedListener;
import model.services.SellerService;

public class SellerFormController implements Initializable{

	private List<DataChangedListener> listeners = new ArrayList<>();
	
	private Seller entity;
	
	private SellerService service;
	
	@FXML
	TextField txtId;
	
	@FXML
	TextField txtName;
	
	@FXML
	TextField txtEmail;
	
	@FXML
	TextField txtBaseSalary;
	
	@FXML
	TextField txtDepartmentId;
	
	@FXML
	Label lblErrorName;
	
	@FXML
	Label lblErrorEmail;
	
	@FXML
	Label lblErrorBaseSalary;
	
	@FXML
	Label lblErrorDepartmentID;
	
	@FXML
	Button btnSave;
	
	@FXML
	Button btnCancel;
	
	public void subscribeDataChangedListener(DataChangedListener listener) {
		listeners.add(listener);
	}
	
	public void setSeller(Seller seller) {
		this.entity = seller;
	}
	
	public void setService(SellerService service) {
		this.service = service;
	}
	
	public void btnSaveAction(ActionEvent event) {
		
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		
		try {
			service.saveOrUpdate(getData());
			notifyDataChangedListeners();
			Utils.currentStage(event).close();
		}catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
		
	}
	
	public void btnCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
	public void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 50);
	}
	
	public void updateFormat() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		txtEmail.setText(entity.getEmail());
		txtBaseSalary.setText(String.valueOf(entity.getBaseSalary()));
		txtDepartmentId.setText(String.valueOf(entity.getDepartment().getId()));
	}
	
	private void notifyDataChangedListeners() {
		for(DataChangedListener listener : listeners) {
			listener.onDataChanged();
		}
	}
	
	private Seller getData() {
		
		Seller sell =  new Seller();

		if(!(txtId.getText() == null) && !txtId.getText().trim().equals("")) {
			sell.setId(Integer.parseInt(txtId.getText()));
		}
		
		sell.setName(txtName.getText());
		sell.setBirthDate(new Date());
		sell.setEmail(txtEmail.getText());
		sell.setBaseSalary(Double.parseDouble(txtBaseSalary.getText()));
		sell.setDepartment(new Department(null, Integer.parseInt(txtDepartmentId.getText())));
		return sell;
	}
} 
