package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import model.dao.PersonDao;
import model.entities.Person;

public class PersonDaoJDBC implements PersonDao {

	private Connection conn;
	
	public PersonDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Person obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO person "
					+ "(Name, BirthDate) "
					+ "VALUES "
					+ "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setDate(2, new java.sql.Date(obj.getBirthDate().getTime()));
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Person obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE person "
					+ "SET Name = ?, BirthDate = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setDate(2, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setInt(3, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM person WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Person findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT person.* "
					+ "FROM person "
					+ "WHERE person.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Person obj = instantiatePerson(rs);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Person instantiatePerson(ResultSet rs) throws SQLException {
		Person obj = new Person();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setBirthDate(new java.util.Date(rs.getTimestamp("BirthDate").getTime()));
		return obj;
	}

	@Override
	public List<Person> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT person.* "
					+ "FROM person "
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Person> list = new ArrayList<>();
			
			while (rs.next()) {		
				Person obj = instantiatePerson(rs);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
