package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Chatroommember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	private String Username;
	private String Password;
	
	public Chatroommember() {
	}
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String name) {
		this.Username = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String pw) {
		this.Password = pw;
	}
	
	@Override
	public String toString() {
		return "{" + "\"ID\":" + ID + ", \"Username\":\"" + Username + "\"" + ", \"Password\":\"" + Password + '}';
	}
	
}
