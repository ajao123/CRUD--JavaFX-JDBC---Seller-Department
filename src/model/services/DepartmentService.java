package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDAO;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDAO departments = DaoFactory.createDepartmentDAO(); 
	
	public List<Department> findAll(){
		return departments.findAll();
	}
	
	public void onBtnSaveAction(Department department) {
		if(department.getId() == null) {
			departments.insert(department);
		}else {
			departments.update(department);
		}
	}
	
	public void remove(Department department) {
		departments.deleteById(department.getId());
	}
}
