package com.JavaRestful.models.components;



import org.springframework.stereotype.Component;

@Component
public class AccountModel {
	private String id;
	private String user;
	private String password;
	private String name;
	private String phone;
	private String address;
	private boolean author;

	public AccountModel(){
		this.author =false;
	}

	public AccountModel(String user, String password, String name , String phone, String address, boolean author) {


		this.user = user;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.author = author;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAuthor() {
		return author;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAuthor(boolean author) {
		this.author = author;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}




}
