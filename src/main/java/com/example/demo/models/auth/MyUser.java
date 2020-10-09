package com.example.demo.models.auth;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.mapping.Array;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MyUser")
@Getter @Setter @NoArgsConstructor
public class MyUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "UsId")
	@Setter(value = AccessLevel.NONE)
	private int usId;
	
	//TODO add Pattern
	@Column(name = "Pass")
	private String password;
	
	//TODO add Pattern
	@Column(name = "UName")
	private String username;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<MyRole> allRoles;


	public MyUser(String password, String username, Collection<MyRole> allRoles) {
		super();
		this.password = password;
		this.username = username;
		this.allRoles = allRoles;
	}

	public MyUser(String password, String username) {
		super();
		this.password = password;
		this.username = username;
		this.allRoles = new ArrayList<>();
	}
	
	public void addNewRoleToUser(MyRole role) {
		
		allRoles.add(role);
	}
	
}
