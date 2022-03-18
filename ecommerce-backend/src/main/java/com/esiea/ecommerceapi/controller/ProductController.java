package com.esiea.ecommerceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esiea.ecommerceapi.model.Product;
import com.esiea.ecommerceapi.service.NotAllowedException;
import com.esiea.ecommerceapi.service.NotFoundException;
import com.esiea.ecommerceapi.service.ProductService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("") // @RequestMapping(method = RequestMethod.GET, value = "")
	public Iterable<Product> getProducts() {
		return productService.getProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
		try {
			Product p = productService.getProduct(id);
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable("name") String name) {
		try {
			Product p = productService.getProductByName(name);
			return new ResponseEntity<Product>(p, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			product = productService.create(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NotAllowedException exception) {
			return new ResponseEntity<Product>(product, HttpStatus.FORBIDDEN);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
		try {
			productService.deleteProduct(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NotFoundException exception) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("")
	public ResponseEntity<Product> replaceProduct(@RequestBody Product product) {
		try {
			product = productService.update(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PatchMapping("")
	public ResponseEntity<Product> partialReplaceProduct(@RequestBody Product product) {
		try {
			Product existingProduct = productService.getProduct(product.getId());
			if (product.getName() != null && !product.getName().equals(existingProduct.getName())) {
				existingProduct.setName(product.getName());
			}
			if (product.getDescription() != null && !product.getDescription().equals(existingProduct.getDescription())) {
				existingProduct.setDescription(product.getDescription());
			}
			if (product.getCost() != null && !product.getCost().equals(existingProduct.getCost())) {
				existingProduct.setCost(product.getCost());
			}
			existingProduct = productService.update(existingProduct);
			return new ResponseEntity<Product>(existingProduct, HttpStatus.OK);
		} catch (NotFoundException exception) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
