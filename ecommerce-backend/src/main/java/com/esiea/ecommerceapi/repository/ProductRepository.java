package com.esiea.ecommerceapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esiea.ecommerceapi.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	public Optional<Product> findByName(String name); // requete derivee
	
	@Query(value = "SELECT * FROM products WHERE name = :name", nativeQuery = true)
	public Optional<Product> findByNameNative(@Param("name") String name); // requete native
}
