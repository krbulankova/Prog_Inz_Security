package com.example.demo.models.validations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import com.example.demo.models.Product;
import com.example.demo.models.enums.ProductType;

class ProductValidationTests {

	
	
	@Test
	void testValidation() {
		Product p1 = new Product("computer", 129.44f, ProductType.ComputerType);
		ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
		Validator val = valF.getValidator();
		
		Set<ConstraintViolation<Product>> result =  val.validate(p1);
		assertEquals(0, result.size());
		
		
	}
	
	@Test
	void testValidationWrongTitle() {
		Product p1 = new Product("comp768564uter", 129.44f, ProductType.ComputerType);
		ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
		Validator val = valF.getValidator();
		
		Set<ConstraintViolation<Product>> result =  val.validate(p1);
		assertEquals(1, result.size());
		
		String patternWrongMessage = result.iterator().next().getMessage();
		assertEquals("Only letters and spaces allowed", patternWrongMessage);
	}
	
	@Test
	void testValidationWrongPrice() {
		Product p1 = new Product("computer", 10000, ProductType.ComputerType);
		ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
		Validator val = valF.getValidator();
		
		Set<ConstraintViolation<Product>> result =  val.validate(p1);
		assertEquals(1, result.size());
		
		String patternWrongMessage = result.iterator().next().getMessage();
		System.out.println(patternWrongMessage);
		assertEquals("must be less than or equal to 1000", patternWrongMessage);
	}
	@Test
	void testValidationWrongAll() {
		Product p1 = new Product("comp768564uter", 10000f, null);
		ValidatorFactory valF = Validation.buildDefaultValidatorFactory();
		Validator val = valF.getValidator();
		
		Set<ConstraintViolation<Product>> result =  val.validate(p1);
		assertEquals(3, result.size());
		
	}

}
