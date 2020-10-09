package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ManyToAny;

import com.example.demo.models.enums.ProductType;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity  @Getter @Setter @NoArgsConstructor @ToString
@Table(name="ProductTable")
public class Product {
	//1.variables
	
	@Column(name="Title")
	@Size(min = 3, max = 20)
	@Pattern(regexp="[a-zA-Z\\s]+$", message = "Only letters and spaces allowed")
	private String title;
	
	@Column(name="Price")
	@Min(0)
	@Max(1000)
	private float price;
	
	@NotNull
	@Column(name="ProdType")
	private ProductType type;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="Cid")//link to Cid column not variable
	private Customer customer;
	
	public Product(String title, float price, ProductType type) {
		this.title = title;
		if(price>=0)
			this.price = price;
		else
			this.price = 0;
		this.type = (type!=null)? type : ProductType.OtherType;
	}
	
	
	
	//3. getters and setters
	
	
	public Product(
			 String title, float price, ProductType type, Customer customer) {
		this.title = title;
		this.price = price;
		this.type = type;
		this.customer = customer;
	}
	
	
}
