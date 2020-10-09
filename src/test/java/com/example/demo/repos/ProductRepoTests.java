package com.example.demo.repos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.models.Product;
import com.example.demo.models.enums.ProductType;


@DataJpaTest
class ProductRepoTests {

	@Autowired
	IProductRepo prodRepo;
	
	
	//insert test
	@Test
	public void testInsert()
	{
		Product p1 = new Product("computer", 129.44f, ProductType.ComputerType);
		prodRepo.save(p1);
		
		Product returnedFromRepo = prodRepo.findByTitleAndPriceAndType(p1.getTitle(), p1.getPrice(), p1.getType());
		assertNotEquals(null, returnedFromRepo);
		assertEquals("computer", returnedFromRepo.getTitle());
		assertEquals(129.44f, returnedFromRepo.getPrice(), 0.01f);
		assertEquals(ProductType.ComputerType, returnedFromRepo.getType());
	
	}
	
	//update test
	@Test
	public void testUpdate()
	{
		Product p1 = new Product("computer", 129.44f, ProductType.ComputerType);
		prodRepo.save(p1);
		
		Product returnedFromRepo = prodRepo.findByTitleAndPriceAndType(p1.getTitle(), p1.getPrice(), p1.getType());
		returnedFromRepo.setPrice(99.99f);
		prodRepo.save(returnedFromRepo);//object with the same id
		
		Product updatedProduct = prodRepo.findById(returnedFromRepo.getId()).get();
		assertNotNull(updatedProduct);
		assertEquals(99.99f, updatedProduct.getPrice());
		
	}
	
	
	
	
	//delete test
	@Test
	public void testDelete()
	{
		Product p1 = new Product("computer", 129.44f, ProductType.ComputerType);
		prodRepo.save(p1);
		
		Product returnedFromRepo = prodRepo.findByTitleAndPriceAndType(p1.getTitle(), p1.getPrice(), p1.getType());
	//	prodRepo.deleteById(returnedFromRepo.getId());
		prodRepo.delete(returnedFromRepo);
		
		Product deletedProduct = prodRepo.findByTitleAndPriceAndType(p1.getTitle(), p1.getPrice(), p1.getType());
		
		assertNull(deletedProduct);
		//TODO it is possible to use assertThrows(expectedType, executable)
		//if findById is used
		
		//assertEquals(null, deletedProduct);
	}
	
	
	
	
	
	
	
	
	
}
