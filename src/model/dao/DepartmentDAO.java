package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDAO {
	void insert(Department department);
	void update(Department department);
	Department select(Integer id);
	void deleteById(Integer id);
	List<Department> findAll();
}
