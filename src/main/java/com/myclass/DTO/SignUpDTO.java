package com.myclass.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class SignUpDTO {
	private  String ID;
	@NotBlank (message = "Nhập Vô Đi Bố ")
	@Email (message =" Nhập Đúng Định Dạng Giùm Bố Ơi ")
	private String email;
	@NotBlank (message="Nhập Name Đi Bố")
	private String fullname;
	@NotBlank (message="")
	@Length(min = 6)
	private String password;
	
	private String confirm;
	private String roleId;
	
	public SignUpDTO(String email, String fullname, String password, String confirm, String roleId) {
		super();
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.confirm = confirm;
		this.roleId = roleId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public SignUpDTO() {}

	
}
