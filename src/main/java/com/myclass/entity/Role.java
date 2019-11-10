package com.myclass.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
@SuppressWarnings("deprecation")
@Entity
@Table(name="roles")
public class Role {
	@Id
	@GeneratedValue
	
	private String id;
	
	@NotBlank(message = "Khong duoc bo trong")
	private String name;
	@NotBlank(message = "name khong duoc rong")
	@Min(value = 4, message = "ten phai lon hon 4")
	
	private String description;

	public Role() {
	}

	public Role(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
