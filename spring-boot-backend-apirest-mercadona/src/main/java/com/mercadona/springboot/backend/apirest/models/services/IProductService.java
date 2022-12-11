package com.mercadona.springboot.backend.apirest.models.services;

import java.util.List;

import com.mercadona.springboot.backend.apirest.models.entities.Product;

public interface IProductService {
	
	public List<Product> findAll();
	
	public Product findById(Long id);
	
	public Product findByCode(String code);
	
	public Product save(Product product);
	
	public void delete(Long id);
}
