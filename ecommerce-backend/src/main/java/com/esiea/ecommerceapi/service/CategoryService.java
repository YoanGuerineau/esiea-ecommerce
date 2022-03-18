package com.esiea.ecommerceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esiea.ecommerceapi.model.Category;
import com.esiea.ecommerceapi.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Iterable<Category> getCategories() {
		return categoryRepository.findAll();
	}
}
