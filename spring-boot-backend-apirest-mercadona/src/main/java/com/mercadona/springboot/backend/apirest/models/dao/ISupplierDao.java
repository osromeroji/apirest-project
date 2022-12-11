package com.mercadona.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mercadona.springboot.backend.apirest.models.entities.Supplier;

public interface ISupplierDao extends CrudRepository<Supplier, Long> {
	
	//@Query("SELECT s FROM Supplier s WHERE s.code=?1")
	public Supplier findTopByCode(String code);
}
