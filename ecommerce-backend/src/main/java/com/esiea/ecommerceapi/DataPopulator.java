package com.esiea.ecommerceapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.esiea.ecommerceapi.model.Product;
import com.esiea.ecommerceapi.repository.ProductRepository;


//This Data Populator is not used anymore but it's an alternative to the sql files under src/main/resources
@Component
public class DataPopulator implements CommandLineRunner {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void run(String... args) throws Exception {
		/*
		Product p1 = new Product(Long.valueOf(1), "First product", "This is the first product", 500);
		Product p2 = new Product(Long.valueOf(2), "Second product", "This is the second product", 250);

		productRepository.save(p1);
		productRepository.save(p2);
		*/
		
		
	}
	
}
