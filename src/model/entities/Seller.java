package model.entities;

import java.io.Serializable;
import java.util.Objects;

import model.interfaces.Trabalhador;

public class Seller extends Person implements Serializable, Trabalhador {

	private static final long serialVersionUID = 1L;

	private String email;
	private Double baseSalary;
	private Department department;

	public Seller() {
	}

	public Seller(String email, Double baseSalary, Department department) {
		this.email = email;
		this.baseSalary = baseSalary;
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(baseSalary, email);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(baseSalary, other.baseSalary)
				&& Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "Seller [email=" + email + ", baseSalary=" + baseSalary + ", department=" + department +"]";
	}

	@Override
	public void trabalhar() {
		// TODO Auto-generated method stub
		
	}
}
