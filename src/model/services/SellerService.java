package model.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerService {
	
	private SellerDAO sellers = DaoFactory.createSellerDAO();
	
	public List<Seller> findAll(){
		return sellers.findAll();
	}
	
	public void saveOrUpdate(Seller seller) {
		if(seller.getId() == null) {
			sellers.insert(seller);
		}else {
			sellers.update(seller);
		}
	}
	
	public void remove(Seller seller) {
		sellers.deleteById(seller.getId());
	}
}
