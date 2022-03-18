package com.esiea.ecommerceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esiea.ecommerceapi.model.Category;
import com.esiea.ecommerceapi.service.CategoryService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/private/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public Iterable<Category> getCategories() {
		return categoryService.getCategories();
	}
	
	// TODO : Ecrire toutes les méthodes pour implémenter le CRUD
	
}
