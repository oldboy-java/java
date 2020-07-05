package com.tk.annotation.domain;

import com.tk.annotation.anno.Property;
import com.tk.annotation.anno.Table;

@Table(value="t_user")
public class User {

	@Property(value = "f_id")
	private int id;
	
	@Property(value = "f_name")
	private String name;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
