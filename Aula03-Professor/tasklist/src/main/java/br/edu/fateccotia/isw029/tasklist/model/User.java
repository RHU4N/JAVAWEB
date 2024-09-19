package br.edu.fateccotia.isw029.tasklist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String usernaem;
	private String password;
	private String email;
	
	//Constructors
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String usernaem, String password, String email) {
		super();
		this.id = id;
		this.usernaem = usernaem;
		this.password = password;
		this.email = email;
	}
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsernaem() {
		return usernaem;
	}

	public void setUsernaem(String usernaem) {
		this.usernaem = usernaem;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
