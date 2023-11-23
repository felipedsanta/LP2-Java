package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.StoreDao;
import model.entities.Store;

public class StoreService {

	private StoreDao dao = DaoFactory.createStoreDao();

	public List<Store> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Store obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Store obj) {
		 dao.deleteById(obj.getId());
	}
}
