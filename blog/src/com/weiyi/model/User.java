package com.weiyi.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String name;
	private String tel;
	private String email;
	private int level;
	
	public User(int id, String username, String password, String name, String tel, String email, int level) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.level = level;
	}
	
	
	
	public User() {
		super();
	}



	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", tel="
				+ tel + ", email=" + email + "]";
	}
	
	
	
	
}
