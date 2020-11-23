package com.JavaRestful.models.components;



import com.JavaRestful.models.requests.account.AccountInfoChange;
import com.JavaRestful.models.requests.account.RegisterByAdminReq;
import com.JavaRestful.models.requests.account.RegisterByUserReq;
import org.springframework.stereotype.Component;

@Component
public class AccountModel {
	private String id;
	private String user;
	private String password;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String idCustomer;
	private boolean author;



	public AccountModel(){
		this.author =false;
	}

	public AccountModel(RegisterByUserReq registerByUserReq){
		this.user = registerByUserReq.getUser();
		this.password = registerByUserReq.getPassword();
		this.name = registerByUserReq.getName();
		this.phone = registerByUserReq.getPhone();
		this.email = registerByUserReq.getEmail();
		this.address = registerByUserReq.getAddress();
		this.author = false;
	}

	public AccountModel(RegisterByAdminReq registerByAdminReq){
		this.user = registerByAdminReq.getUser();
		this.password = registerByAdminReq.getPassword();
		this.name = registerByAdminReq.getName();
		this.phone = registerByAdminReq.getPhone();
		this.email = registerByAdminReq.getEmail();
		this.address = registerByAdminReq.getAddress();
		this.author = registerByAdminReq.isAuthor();
	}

	public AccountModel changeData(AccountInfoChange accountInfoChange){
		this.name  = accountInfoChange.getName();
		this.phone = accountInfoChange.getPhone();
		this.address = accountInfoChange.getAddress();
		this.email = accountInfoChange.getEmail();
		return this;
	}

	public String getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
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
