package gui;

import java.net.URL;
import java.util.ArrayList;
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

import model.listeners.DataChangedListener;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable{

	private Department entity;
	
	private DepartmentService service;

	private List<DataChangedListener> listeners = new ArrayList<>();
	
	@FXML
	private TextField tfId;
	
	@FXML
	private TextField tfName;
	
	@FXML
	private Label lblError;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnCancel;
	
	
	public void subscribeDataChangedListener(DataChangedListener listener) {
		listeners.add(listener);
	}
	
	public void setDepartment(Department department) {
		this.entity = department;
	}
	
	public void setService(DepartmentService service) {
		this.service = service;
	}
	
	@FXML
	public void onBtnSaveAction(ActionEvent event) {
		
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getData();
			service.onBtnSaveAction(entity);
			notifyDataChangedListeners();
			Utils.currentStage(event).close();
			
			
		}catch(DbException e) {
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}
		
	}
	
	private void notifyDataChangedListeners() {
		for(DataChangedListener listener : listeners) {
			listener.onDataChanged();
		}
	}
	
	@FXML
	public void onBtnCancelAction() {
		System.out.println("onBtnCancelAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	public void initializeNodes() {
		Constraints.setTextFieldInteger(tfId);
		Constraints.setTextFieldMaxLength(tfName, 50);
	}
	
	public void updateFormat() {
		if(entity ==  null) {
			throw new IllegalStateException("Entity was null");
		}
		
		tfId.setText(String.valueOf(entity.getId()));
		tfName.setText(entity.getName());
	}
	
	private Department getData() {
		Department obj = new Department();
		
		
		obj.setId(Integer.parseInt(tfId.getText()));
		obj.setName(tfName.getText());

		return obj;
	}
}