package com.mercadona.springboot.backend.apirest.models.services;

import java.util.List;

import com.mercadona.springboot.backend.apirest.models.entities.Supplier;

public interface ISupplierService {

	public List<Supplier> findAll();

	public Supplier findById(Long id);
	
	public Supplier findByCode(String code);

	public Supplier save(Supplier supplier);

	public void delete(Long id);
}
