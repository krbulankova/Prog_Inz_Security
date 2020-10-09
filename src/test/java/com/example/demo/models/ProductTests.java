package com.example.demo.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.example.demo.models.enums.ProductType;

class ProductTests {

	Product p1 = new Product("computer", 129.44f, ProductType.ComputerType);
	Product p2 = new Product("7345872648", -0.33f, null);
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testPositiveParams()
	{
		assertEquals("computer", p1.getTitle());
		assertEquals(129.44f, p1.getPrice(), 0.01f);
		assertEquals(ProductType.ComputerType, p1.getType());
	}
	
	@Test
	public void testNegativeParams()
	{
		//assertNotEquals("7345872648", p2.getTitle());
		assertEquals(0, p2.getPrice(), 0.01f);
		assertNotEquals(null, p2.getType());
		assertEquals(ProductType.OtherType, p2.getType());
	}
	
	@Test
	public void testRegex()
	{
		assertTrue(p1.getTitle().matches("[a-zA-Z]+"));
		assertFalse(p2.getTitle().matches("[a-zA-Z]+"));
	}
	
	

}
