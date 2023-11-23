package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import model.interfaces.Trabalhador;

public class Seller extends Employee implements Serializable, Trabalhador {

	private static final long serialVersionUID = 1L;

	private String email;
	private Date birthDate;
	private Boolean status;

	public Seller() {
	}

	public Seller(String email, Date birthDate, Boolean status) {
		this.email = email;
		this.birthDate = birthDate;
		this.status = status;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(birthDate, email);
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
		return Objects.equals(birthDate, other.birthDate)
				&& Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "Seller [email=" + email + ", birthDate=" + birthDate + "]";
	}

	@Override
	public void trabalhar() {
		// TODO Auto-generated method stub
		
	}
}
