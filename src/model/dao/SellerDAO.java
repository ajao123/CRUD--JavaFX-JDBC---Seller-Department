package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDAO {
	void insert(Seller seller);
	void update(Seller seller);
	Seller select(Integer id);
	void deleteById(Integer id);
	List<Seller> findAll();
}
