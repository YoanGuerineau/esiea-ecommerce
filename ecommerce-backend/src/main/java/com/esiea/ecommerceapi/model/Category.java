package com.esiea.ecommerceapi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;
	private String name;
	
	@JsonIgnore
	@ManyToMany(
			fetch = FetchType.LAZY, // performance 
			cascade = { 
					CascadeType.PERSIST, // creation
					CascadeType.MERGE // modification
			}
	)
	@JoinTable(
			name = "category_product", // nom de la table de jointure
			joinColumns = @JoinColumn(name = "category_id"), // cle etrangere dans la table de jointure correspondant a la cle primaire de la classe courante (category)
            inverseJoinColumns = @JoinColumn(name = "product_id") // cle etrangere dans la table de jointure correspondant a la cle primaire de la classe en relation (product)
	)
	private List<Product> products = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}	
}
