package com.example.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.models.enums.ProductType;
import com.example.demo.repos.ICustomerRepo;
import com.example.demo.repos.IProductRepo;



@SpringBootApplication
public class MiniWebStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniWebStoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner saveData(ICustomerRepo custRepo, IProductRepo prodRepo)
	{
		return (args) ->{
			Customer cust1 = new Customer("Janis", 32);
			Customer cust2 = new Customer("Baiba", 76);
			
			custRepo.save(cust1);
			custRepo.save(cust2);
			
			prodRepo.save(new Product("bread", 1.99f, ProductType.BakeryType, cust1));
			prodRepo.save(new Product("window", 56.67f, ProductType.OtherType));
			prodRepo.save(new Product("apple", 0.56f, ProductType.OtherType, cust2));

		};
	}
	
	
}
