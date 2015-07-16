package me.pedrazas.benchmark.hazelcast;

import java.io.Serializable;

public class Customer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8953822441100995034L;
	private String name;
	private String id;
	private String email;
	private int age;
	
	
	public Customer(String id) {
		this.id = id;
	}
	

	public Customer(String name, String id, String email, int age) {
		super();
		this.name = name;
		this.id = id;
		this.email = email;
		this.age = age;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
