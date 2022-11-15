package com.shoes.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Customer { 
	@Id
	private String email;
	private String name;
	private String password;
	private long contact;

	
	public Customer() {
		super();
	}
	
	public Customer(String email, String name, String password, long contact) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.contact = contact;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Customer [email=" + email + ", name=" + name + ", password=" + password + ", contact=" + contact + "]";
	}
	
	
}
