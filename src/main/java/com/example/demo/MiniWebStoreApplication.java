package com.example.demo;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.models.auth.MyRole;
import com.example.demo.models.auth.MyUser;
import com.example.demo.models.enums.ProductType;
import com.example.demo.repos.ICustomerRepo;
import com.example.demo.repos.IMyRoleRepo;
import com.example.demo.repos.IMyUserRepo;
import com.example.demo.repos.IProductRepo;



@SpringBootApplication
public class MiniWebStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniWebStoreApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {//slepj paroli
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CommandLineRunner saveData(ICustomerRepo custRepo, IProductRepo prodRepo, IMyUserRepo userRepo, IMyRoleRepo roleRepo)
	{
		return (args) ->{
			
			MyUser u1 = new MyUser(passwordEncoder().encode("123"), "karina");
			MyUser u2 = new MyUser(passwordEncoder().encode("321"), "janis");
			userRepo.save(u1);
			userRepo.save(u2);
			
			MyRole r1 = new MyRole("ADMIN");
			MyRole r2 = new MyRole("USER");
			roleRepo.save(r1);
			roleRepo.save(r2);
			
			MyUser user1FromDB = userRepo.findByUsername("karina");
			MyRole role1FromDB = roleRepo.findByTitle("USER");
			
			user1FromDB.addNewRoleToUser(role1FromDB);
			userRepo.save(user1FromDB);
			
			role1FromDB.addNewUserToRole(user1FromDB);
			roleRepo.save(role1FromDB);
			
			
			MyUser user2FromDB = userRepo.findByUsername("janis");
			MyRole role2FromDB = roleRepo.findByTitle("ADMIN");
			
			user2FromDB.addNewRoleToUser(role2FromDB);
			user2FromDB.addNewRoleToUser(role1FromDB);
			userRepo.save(user2FromDB);
			
			role2FromDB.addNewUserToRole(user2FromDB);
			role1FromDB.addNewUserToRole(user2FromDB);
			roleRepo.save(role2FromDB);
			roleRepo.save(role1FromDB);
		
			
			
			
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
