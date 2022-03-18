package com.esiea.ecommerceapi.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.esiea.ecommerceapi.model.Product;
import com.esiea.ecommerceapi.repository.ProductRepository;

@Service
public class ProductService {

	Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	public Iterable<Product> getProducts() {
		return productRepository.findAll();
	}
	
	public Product getProduct(Long id) throws NotFoundException {
		Optional<Product> result = productRepository.findById(id);
		if (result.isPresent()) {
			return result.get(); 
		} else {
			throw new NotFoundException();
		}
	}

	public Product getProductByName(String name) throws NotFoundException {
		Optional<Product> result = productRepository.findByName(name);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new NotFoundException();
		}
	}

	public Product create(Product product) throws NotAllowedException {
		if (product.getId() == null) {
			return productRepository.save(product);			
		} else {
			throw new NotAllowedException();
		}
	}
	
	public Product update(Product product) throws NotFoundException {
		getProduct(product.getId());
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) throws NotFoundException {
		try {
			productRepository.deleteById(id);					
		} catch (EmptyResultDataAccessException exception) {
			logger.warn("Product doesn't exist!");
			throw new NotFoundException();
		}
	}
}
