package com.mercadona.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mercadona.springboot.backend.apirest.models.entities.Supplier;

public interface ISupplierDao extends CrudRepository<Supplier, Long> {

}
