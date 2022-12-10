package com.mercadona.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mercadona.springboot.backend.apirest.models.entities.Supplier;

public interface ISupplierDao extends CrudRepository<Supplier, Long> {
	
	@Query("SELECT s FROM Supplier s WHERE s.code=?1")
	public List<Supplier> findByCode(String code);
}
