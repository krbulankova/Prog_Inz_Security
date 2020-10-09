package com.example.demo.models.auth;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MyRole")
@Getter @Setter @NoArgsConstructor
public class MyRole {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "RoId")
	@Setter(value = AccessLevel.NONE)
	private int roId;
	
	@Column(name = "RoleTitle")
	private String title;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "MyUser_MyRole", joinColumns = @JoinColumn(columnDefinition = "UsId"), inverseJoinColumns = @JoinColumn(columnDefinition = "RoId"))
	private Collection<MyUser> allUsers;


	public MyRole(String title) {
		super();
		this.title = title;
	}
	
	

}
