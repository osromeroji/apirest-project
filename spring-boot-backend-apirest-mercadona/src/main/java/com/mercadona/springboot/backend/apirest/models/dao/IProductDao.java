package com.mercadona.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mercadona.springboot.backend.apirest.models.entities.Product;

public interface IProductDao extends CrudRepository<Product, Long> {
	
	public Product findTopByCode(String code);
}
