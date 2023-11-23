package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.PersonDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.dao.impl.StoreDaoJDBC;

public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
	
	public static PersonDao createPersonDao() {
		return new PersonDaoJDBC(DB.getConnection());
	}
	
	public static StoreDao createStoreDao() {
		return new StoreDaoJDBC(DB.getConnection());
	}
}
