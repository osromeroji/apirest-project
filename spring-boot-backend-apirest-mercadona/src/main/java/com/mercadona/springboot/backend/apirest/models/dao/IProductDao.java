package com.mercadona.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mercadona.springboot.backend.apirest.models.entities.Product;
import com.mercadona.springboot.backend.apirest.models.entities.Supplier;

public interface IProductDao extends CrudRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.code=?1")
	public List<Product> findByCode(String code);
}
